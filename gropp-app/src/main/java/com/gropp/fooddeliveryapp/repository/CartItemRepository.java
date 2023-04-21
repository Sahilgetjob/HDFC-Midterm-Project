package com.gropp.fooddeliveryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gropp.fooddeliveryapp.entity.Cart;
import com.gropp.fooddeliveryapp.entity.CartItem;
import com.gropp.fooddeliveryapp.entity.Menu;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	void deleteByCart(Cart cart);
	List<CartItem> findByMenu(Menu menu);
}
