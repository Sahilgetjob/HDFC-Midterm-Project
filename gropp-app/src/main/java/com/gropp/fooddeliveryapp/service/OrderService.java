package com.gropp.fooddeliveryapp.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.DTO.OrderDTO;
import com.gropp.fooddeliveryapp.DTO.OrderMapper;
import com.gropp.fooddeliveryapp.entity.Address;
import com.gropp.fooddeliveryapp.entity.Cart;
import com.gropp.fooddeliveryapp.entity.CartItem;
import com.gropp.fooddeliveryapp.entity.Driver;
import com.gropp.fooddeliveryapp.entity.Order;
import com.gropp.fooddeliveryapp.entity.OrderItem;
import com.gropp.fooddeliveryapp.entity.Payment;
import com.gropp.fooddeliveryapp.entity.Rating;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.entity.User;
import com.gropp.fooddeliveryapp.entity.enums.OrderStatus;
import com.gropp.fooddeliveryapp.entity.enums.PaymentMethod;
import com.gropp.fooddeliveryapp.entity.enums.PaymentStatus;
import com.gropp.fooddeliveryapp.exceptionhandler.EmptyCartFoundException;
import com.gropp.fooddeliveryapp.exceptionhandler.OrderStatusException;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.AddressRepository;
import com.gropp.fooddeliveryapp.repository.CartRepository;
import com.gropp.fooddeliveryapp.repository.DriverRepository;
import com.gropp.fooddeliveryapp.repository.OrderItemRepository;
import com.gropp.fooddeliveryapp.repository.OrderRepository;
import com.gropp.fooddeliveryapp.repository.PaymentRepository;
import com.gropp.fooddeliveryapp.repository.RatingRepository;
import com.gropp.fooddeliveryapp.service.interfaces.ICartService;
import com.gropp.fooddeliveryapp.service.interfaces.IOrderService;
import com.gropp.fooddeliveryapp.service.interfaces.IRestaurantService;
import com.gropp.fooddeliveryapp.service.interfaces.IUserService;

@Service
public class OrderService implements IOrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRestaurantService restaurantService;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public Order getOrder(int orderId) {
		return  orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
	}
	
	@Override
	public OrderDTO createOrder(int userId, int restaurantId, int addressId) {
		Cart cart = cartRepository.findByUserIdAndRestaurantId(userId, restaurantId).orElseThrow(() -> new ResourceNotFoundException("Cart not found for User"));
		User user = userService.getUser(userId);
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found"));
		
		if(!cart.getCartItems().isEmpty())
		{
			if(user.getAddresses().stream()
								  .filter(n -> n.getId() == address.getId())
								  .findFirst().orElse(null) != null)
			{
					Order order = new Order();
					
					order.setUser(user);
					order.setRestaurant(restaurant);
					order.setAddress(address);
					order.setOrderTotal(cart.getCartTotal());
					order.setOrderDate(LocalDate.now());
					order.setOrderStatus(OrderStatus.NOT_PLACED);
					
					orderRepository.save(order);
					
					for(CartItem cartItem:cart.getCartItems()) 
					{
						String menuItemName = cartItem.getMenu().getItemName();
						double menuItemPrice = cartItem.getMenu().getPrice();
						OrderItem orderItem = new OrderItem();
						
						orderItem.setMenuName(menuItemName);
						orderItem.setPrice(menuItemPrice);
						orderItem.setOrder(order);
						orderItem.setQuantity(cartItem.getQuantity());
						orderItem.setTotal(cartItem.getTotal());
						orderItemRepository.save(orderItem);
						
					}
					cartService.clearCart(userId, restaurantId);
					
					
					return orderMapper.toDto(orderRepository.save(order));
			}
			else
				throw new ResourceNotFoundException("Address doesn't belong to cart user");
		}
		else
			throw new EmptyCartFoundException("Cart is Empty, add food items to cart for ordering");
	}
	
	@Override
	public OrderDTO addPaymentForOrder(int orderId, PaymentMethod paymentMethod) {
		Order order = getOrder(orderId);
		if(order.getOrderStatus().getStatus().equals("Not Placed"))
		{
			Payment payment = new Payment();
			payment.setPaymentMethod(paymentMethod);
			payment.setAmount(order.getOrderTotal());
			payment.setTransactionId(Stream.generate(new Random()::nextInt)
										   .limit(12).map(String::valueOf)
										   .findFirst().orElse(null));
			payment.setPaymentStatus(PaymentStatus.COMPLETED);
			payment.setOrder(order);
			
			if(payment.getPaymentStatus().getStatus().equals("Completed"))
				order.setOrderStatus(OrderStatus.PLACED);
			else
				order.setOrderStatus(OrderStatus.FAILED);
			
			paymentRepository.save(payment);
			
			return orderMapper.toDto(orderRepository.save(order));
		}
		throw new OrderStatusException("Order Status is invalid");
	}
	
	@Override
	public OrderDTO addDriverForOrder(int orderId) {
		Order order = getOrder(orderId);
		if(order.getOrderStatus().getStatus().equals("Placed"))
		{
			//Simulating process of selecting a driver
			List<Driver> drivers = driverRepository.findAll();
			Collections.shuffle(drivers);
			Driver driver = drivers.get(0);
			
			order.setDriver(driver);
			order.setOrderStatus(OrderStatus.IN_TRANSIT);
			
			CompletableFuture.runAsync(() -> {
												try {
													Thread.sleep(30000);
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												order.setOrderStatus(OrderStatus.DELIVERED);
												orderRepository.save(order);
			});
			return orderMapper.toDto(orderRepository.save(order));
			
		}
		throw new OrderStatusException("Order Status is invalid");
	}
	
	@Override
	public OrderDTO addRatingForOrder(int orderId, double ratingScore, String comment) {
		Order order = getOrder(orderId);
		User user = order.getUser();
		Restaurant restaurant = order.getRestaurant();
		if(order.getOrderStatus().getStatus().equals("Delivered") && order.getRating() == null) {
			Rating rating = new Rating();
			rating.setRatingScore(ratingScore);
			
			if(!comment.equals(null))
				rating.setComment(comment);
			else
				rating.setComment("No Comment");
			
			rating.setOrder(order);
			rating.setUser(user);
			rating.setRestaurant(restaurant);
			
			ratingRepository.save(rating);
			return orderMapper.toDto(order);
		}
		else
			throw new OrderStatusException("Order Status is invalid or Rating has already been given");
	}
	

	@Override
	public List<OrderDTO> findAll(){
		return orderMapper.toListDto(orderRepository.findAll());
	}
	
	@Override
	public OrderDTO findOrderById(int orderId) {
		Order order = getOrder(orderId);
		return orderMapper.toDto(order);
	}
	
	@Override
	public List<OrderDTO> findByUser(User user){
		return orderMapper.toListDto(orderRepository.findByUser(user));
	}
	
	@Override
	public List<OrderDTO> findByRestaurant(Restaurant restaurant){
		return orderMapper.toListDto(orderRepository.findByRestaurant(restaurant));
	}
	
	@Override
	public List<OrderDTO> findByUserAndRstaurant(User user, Restaurant restaurant){
		return orderMapper.toListDto(orderRepository.findByUserAndRestaurant(user, restaurant));
	}
	
	@Override
	public List<OrderDTO> findByDriver(Driver driver){
		return orderMapper.toListDto(orderRepository.findByDriver(driver));
	}
	
}
