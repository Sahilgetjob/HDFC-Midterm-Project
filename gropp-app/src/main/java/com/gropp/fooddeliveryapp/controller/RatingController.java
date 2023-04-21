package com.gropp.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gropp.fooddeliveryapp.DTO.RatingDTO;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.entity.User;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.RatingRepository;
import com.gropp.fooddeliveryapp.repository.RestaurantRepository;
import com.gropp.fooddeliveryapp.repository.UserRepository;
import com.gropp.fooddeliveryapp.service.RatingService;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	
	@PutMapping("/updateRating/{ratingId}/{ratingScore}/{comment}")
	public RatingDTO updateRating(@PathVariable int ratingId, @PathVariable double ratingScore, @PathVariable String comment) {
		return ratingService.updateRating(ratingId, ratingScore, comment);
	}
	
	
	@DeleteMapping("/deleteRatingById/{ratingId}")
	public ResponseEntity<String> delete(@PathVariable int ratingId) {
		if(ratingRepository.findById(ratingId).isPresent())
		{
			ratingService.delete(ratingId);
			return ResponseEntity.ok("Rating deleted");
		}
		else
			return ResponseEntity.badRequest().body("Rating doesn't exist");
	}
	
	@GetMapping("/findAll")
	public List<RatingDTO> findAll(){
		return ratingService.findAll();
	}
	
	
	@GetMapping("/findByUserAndRestaurant/{userId}/{restaurantId}")
	public List<RatingDTO> findByUserAndRestaurant(@PathVariable int userId, @PathVariable int restaurantId){
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		return ratingService.findByUserAndRestaurant(user, restaurant);
	}
	
	@GetMapping("/findByUser/{userId}")
	public List<RatingDTO> findByUser(@PathVariable int userId){
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return ratingService.findByUser(user);
	}
	
	@GetMapping("/findByRestaurant/{restaurantId}")
	public List<RatingDTO> findByRestaurant(@PathVariable int restaurantId){
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
		
		return ratingService.findByRestaurant(restaurant);
	}
	
	
	

}
