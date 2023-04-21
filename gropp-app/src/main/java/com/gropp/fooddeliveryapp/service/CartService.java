package com.gropp.fooddeliveryapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.DTO.CartDTO;
import com.gropp.fooddeliveryapp.DTO.CartMapper;
import com.gropp.fooddeliveryapp.entity.Cart;
import com.gropp.fooddeliveryapp.entity.CartItem;
import com.gropp.fooddeliveryapp.entity.Menu;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.entity.User;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.CartRepository;
import com.gropp.fooddeliveryapp.repository.MenuRepository;
import com.gropp.fooddeliveryapp.repository.RestaurantRepository;
import com.gropp.fooddeliveryapp.repository.UserRepository;
import com.gropp.fooddeliveryapp.service.interfaces.ICartItemService;
import com.gropp.fooddeliveryapp.service.interfaces.ICartService;

@Service
public class CartService implements ICartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private ICartItemService cartItemService;
	
	@Autowired
	private CartMapper cartMapper;
	
	
	@Override
	public Cart getCart(int userId, int restaurantId) {
		Cart cart = cartRepository.findByUserIdAndRestaurantId(userId, restaurantId).orElse(null);
		if(cart == null)
		{
				Cart newCart = new Cart();
				
				User user = userRepository.findById(userId)
											.orElseThrow(() -> new ResourceNotFoundException("User not found"));;
				Restaurant restaurant = restaurantRepository.findById(restaurantId)
															.orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
				newCart.setUser(user);
				newCart.setRestaurant(restaurant);
				return cartRepository.save(newCart);
		}
		return cart;
	}
	
	@Override
	public CartDTO addToCart(int userId, int restaurantId, int menuId, int quantity) {
		
		Cart cart = getCart(userId, restaurantId);
		Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new ResourceNotFoundException("Menu Item not found"));
		
		if(cart.getRestaurant().getMenuItems().stream()
				  			   .filter(n -> n.getId() == menuId)
				  			   .findFirst().orElse(null) != null)
		{
				CartItem cartItem1 = cart.getCartItems().stream().filter(n -> n.getMenu().getId() == menuId).findFirst().orElse(null);
				
				if(cartItem1 == null)
				{		CartItem cartItem = new CartItem();
						cartItem.setMenu(menu);
						cartItem.setCart(cart);
						cartItem.setQuantity(quantity);
						cartItem.setTotal(menu.getPrice()*quantity);
						cart.getCartItems().add(cartItem);
						cart.setCartTotal(cart.getCartTotal() + cartItem.getTotal());
		
				}
				else {
					cartItem1.setQuantity(cartItem1.getQuantity() + quantity);
					cartItem1.setTotal(cartItem1.getTotal() + quantity*cartItem1.getMenu().getPrice());
					cart.setCartTotal(cart.getCartTotal() + quantity*cartItem1.getMenu().getPrice());
					
					cartItemService.save(cartItem1);
				}
				return cartMapper.toDto(cartRepository.save(cart));
		}
		else
			throw new ResourceNotFoundException("Menu not found in the cart restaurant");
		
	}
	
	@Override
	@Transactional
	public void removeCartItem(int userId, int restaurantId, int cartItemId) {
		
		Cart cart = getCart(userId, restaurantId);
		CartItem cartItem = cartItemService.getCartItem(cartItemId);
		cart.setCartTotal(cart.getCartTotal() - cartItem.getTotal());
		cartItemService.delete(cartItem);
		cart.getCartItems().remove(cartItem);
		cartRepository.save(cart);
		}
	
	
	@Override
	public  CartDTO updateCartItemQuantity(int userId, int restaurantId, int cartItemId, int quantity) {
		
		Cart cart = getCart(userId, restaurantId);
		CartItem cartItem = cartItemService.getCartItem(cartItemId);

		cart.setCartTotal(cart.getCartTotal() - cartItem.getTotal());
		
		cartItem.setQuantity(quantity);
		
		double total = cartItem.getMenu().getPrice()*cartItem.getQuantity();
		cartItem.setTotal(total);
		cart.setCartTotal(cart.getCartTotal() + total);
		cartItemService.save(cartItem);
		return cartMapper.toDto(cartRepository.save(cart));
	}
	
	@Override
	@Transactional
	public void clearCart(int userId, int restaurantId) {
		Cart cart = getCart(userId, restaurantId);
		
		cart.setCartTotal(0.0);
		cartItemService.deleteByCart(cart);
		cart.getCartItems().clear();
	    cartRepository.save(cart);
	
	}
	
	@Override
	public CartDTO findCartById(int id) {
		Cart cart = cartRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
		return cartMapper.toDto(cart);
	}
	
	@Override
	public CartDTO findByUserIdAndRestaurantId(int userId, int restaurantId) {
		Cart cart = cartRepository.findByUserIdAndRestaurantId(userId, restaurantId).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
		return cartMapper.toDto(cart);
	}
	
	@Override
	public List<CartDTO> findAll(){
		return cartMapper.toListDto(cartRepository.findAll());
	}
}
