package com.gropp.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gropp.fooddeliveryapp.DTO.OrderDTO;
import com.gropp.fooddeliveryapp.entity.Driver;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.entity.User;
import com.gropp.fooddeliveryapp.entity.enums.PaymentMethod;
import com.gropp.fooddeliveryapp.service.DriverService;
import com.gropp.fooddeliveryapp.service.OrderService;
import com.gropp.fooddeliveryapp.service.RestaurantService;
import com.gropp.fooddeliveryapp.service.UserService;


@RestController
@RequestMapping("api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private DriverService driverService;
	
	@PostMapping("/createOrder/{userId}/{restaurantId}/{addressId}")
	public OrderDTO createOrder(@PathVariable int userId, @PathVariable int restaurantId, @PathVariable int addressId) {
		
		return orderService.createOrder(userId, restaurantId, addressId);
	}
	
	@PutMapping("/addPaymentForOrder/{orderId}/{paymentMethod}")
	public OrderDTO addPaymentForOrder(@PathVariable int orderId, @RequestParam("paymentMethod") PaymentMethod paymentMethod) {
		return orderService.addPaymentForOrder(orderId, paymentMethod);
	}
	
	@PutMapping("/addDriverForOrder/{orderId}")
	public OrderDTO addDriverForOrder(@PathVariable int orderId) {
		return orderService.addDriverForOrder(orderId);
	}
	
	
	@PutMapping("/addRatingForOrder/{orderId}/{ratingScore}/{comment}")
	public OrderDTO addRatingForOrder(@PathVariable int orderId, @PathVariable double ratingScore, @PathVariable(required = false) String comment) {
		return orderService.addRatingForOrder(orderId, ratingScore, comment);
	}
	
	@GetMapping("/findOrderById/{orderId}")
	public OrderDTO findOrderById(@PathVariable int orderId) {
		return orderService.findOrderById(orderId);
	}
	
	@GetMapping("/findAll")
	public List<OrderDTO> findAll(){
		return orderService.findAll();
	}
	
	@GetMapping("/findByUser/{userId}")
	public List<OrderDTO> findByUser(@PathVariable int userId){
		User user = userService.getUser(userId);
		
		return orderService.findByUser(user);
	}
	
	@GetMapping("/findByRestaurant/{restaurantId}")
	public List<OrderDTO> findByRestaurant(@PathVariable int restaurantId){
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		
		return orderService.findByRestaurant(restaurant);
	}
	
	@GetMapping("/findBy/{userId}/{restaurantId}")
	public List<OrderDTO> findByUserAndRestaurant(@PathVariable int userId, @PathVariable int restaurantId){
		User user = userService.getUser(userId);
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		
		return orderService.findByUserAndRstaurant(user, restaurant);
	}
	
	@GetMapping("/findByDriver/{driverId}")
	public List<OrderDTO> findByDriver(@PathVariable int driverId){
		Driver driver = driverService.getDriver(driverId);
		return orderService.findByDriver(driver);
	}
}
