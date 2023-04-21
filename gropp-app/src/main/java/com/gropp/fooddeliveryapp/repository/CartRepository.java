package com.gropp.fooddeliveryapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gropp.fooddeliveryapp.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByUserIdAndRestaurantId(int userId, int restaurantId);
}
