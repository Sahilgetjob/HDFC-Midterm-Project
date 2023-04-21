package com.gropp.fooddeliveryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gropp.fooddeliveryapp.entity.Rating;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.entity.User;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	List<Rating> findByUserAndRestaurant(User user, Restaurant restaurnant);
	List<Rating> findByUser(User user);
	List<Rating> findByRestaurant(Restaurant restaurant);
}
