package com.gropp.fooddeliveryapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gropp.fooddeliveryapp.entity.Driver;
import com.gropp.fooddeliveryapp.entity.Order;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.entity.User;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUser(User user);
	List<Order> findByRestaurant(Restaurant restaurant);
	List<Order> findByUserAndRestaurant(User user, Restaurant restaurant);
	List<Order> findByDriver(Driver driver);
}
