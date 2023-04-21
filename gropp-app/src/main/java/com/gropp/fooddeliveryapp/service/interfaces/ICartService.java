package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.DTO.CartDTO;
import com.gropp.fooddeliveryapp.entity.Cart;

public interface ICartService {

	Cart getCart(int userId, int restaurantId);

	CartDTO addToCart(int userId, int restaurantId, int menuId, int quantity);

	void removeCartItem(int userId, int restaurantId, int cartItemId);

	CartDTO updateCartItemQuantity(int userId, int restaurantId, int cartItemId, int quantity);

	void clearCart(int userId, int restaurantId);

	CartDTO findCartById(int id);

	CartDTO findByUserIdAndRestaurantId(int userId, int restaurantId);

	List<CartDTO> findAll();

}
