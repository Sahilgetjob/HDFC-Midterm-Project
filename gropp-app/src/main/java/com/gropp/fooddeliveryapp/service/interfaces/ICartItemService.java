package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.entity.Cart;
import com.gropp.fooddeliveryapp.entity.CartItem;

public interface ICartItemService {

	CartItem save(CartItem cartItem);

	CartItem getCartItem(int cartItemId);

	List<CartItem> findByMenu(int menuId);

	List<CartItem> findAll();

	void deleteById(int cartItemId);

	void deleteByCart(Cart cart);

	void delete(CartItem cartItem);

}
