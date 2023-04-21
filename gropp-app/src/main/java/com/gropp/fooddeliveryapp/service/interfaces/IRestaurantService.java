package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.DTO.RestaurantDTO;
import com.gropp.fooddeliveryapp.entity.Restaurant;

public interface IRestaurantService {

	Restaurant getRestaurant(int restaurantId);

	void save(Restaurant restaurant);

	RestaurantDTO createRestaurant(String name, String address, String phone, String description, String cuisineType);

	RestaurantDTO updateRestaurant(int restaurantId, String name, String address, String phone, String description,
			String cuisineType);

	List<RestaurantDTO> findAll();

	RestaurantDTO findByResturantId(int restaurantId);

	void deleteByRestaurantId(int restaurantId);

}
