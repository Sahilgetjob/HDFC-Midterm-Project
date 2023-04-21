package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.DTO.RatingDTO;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.entity.User;

public interface IRatingService {

	
	RatingDTO updateRating(int ratingId, double ratingScore, String comment);

	List<RatingDTO> findAll();

	List<RatingDTO> findByUserAndRestaurant(User user, Restaurant restaurant);

	List<RatingDTO> findByUser(User user);

	List<RatingDTO> findByRestaurant(Restaurant restaurant);

	void delete(int id);


}
