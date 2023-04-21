package com.gropp.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gropp.fooddeliveryapp.DTO.CartDTO;
import com.gropp.fooddeliveryapp.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/{userId}/{restaurantId}/addCartItem/{menuId}/{quantity}")
	public ResponseEntity<String> addToCart(@PathVariable int userId, @PathVariable int restaurantId, @PathVariable int menuId, @PathVariable int quantity) {
		 cartService.addToCart(userId, restaurantId, menuId, quantity);
		 return ResponseEntity.ok("Cart Item added");
	}
	
	@PutMapping("/{userId}/{restaurantId}/{cartItemId}/{quantity}")
	public CartDTO updateCartItemQuantity(@PathVariable int userId, @PathVariable int restaurantId, @PathVariable int cartItemId, @PathVariable int quantity) {
		return cartService.updateCartItemQuantity(userId, restaurantId, cartItemId, quantity);
	}
	
	@DeleteMapping("/cartItems/removeCartItem/{userId}/{restaurantId}/{cartItemId}")
	public ResponseEntity<String> removeCartItem(@PathVariable int userId, @PathVariable int restaurantId, @PathVariable int cartItemId) {
		cartService.removeCartItem(userId, restaurantId, cartItemId);
		return ResponseEntity.ok("Removed");
	}
	
	@GetMapping("/findCart/{cartId}")
	public CartDTO findCartById(@PathVariable int cartId) {
		return cartService.findCartById(cartId);
	}
	
	@GetMapping("/findCart/{userId}/{restaurantId}")
	public CartDTO findCartByUserIdAndRestaurantId(@PathVariable int userId, @PathVariable int restaurantId) {
		return cartService.findByUserIdAndRestaurantId(userId, restaurantId);
	}
	
	@GetMapping("/findAllCarts")
	public List<CartDTO> findAll(){
		return cartService.findAll();
	}
	
	
	@DeleteMapping("/clearCart/{userId}/{restaurantId}")
	public ResponseEntity<String> clearCart(@PathVariable int userId, @PathVariable int restaurantId) {
		cartService.clearCart(userId, restaurantId);
		return ResponseEntity.ok("Cart cleared");
	}
	
	
	
}
