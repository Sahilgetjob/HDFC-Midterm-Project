package com.gropp.fooddeliveryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gropp.fooddeliveryapp.entity.Menu;
import com.gropp.fooddeliveryapp.entity.Restaurant;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

	
	List<Menu> findByRestaurant(Restaurant restaurant);
}
