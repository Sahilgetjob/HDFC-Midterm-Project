package com.gropp.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.DTO.RatingDTO;
import com.gropp.fooddeliveryapp.DTO.RatingMapper;
import com.gropp.fooddeliveryapp.entity.Rating;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.entity.User;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.RatingRepository;
import com.gropp.fooddeliveryapp.service.interfaces.IRatingService;

@Service
public class RatingService implements IRatingService{
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private RatingMapper ratingMapper;
	
	@Override
	public RatingDTO updateRating(int ratingId, double ratingScore, String comment) {
		Optional<Rating> ratingOptional = ratingRepository.findById(ratingId);

		if(ratingOptional.isPresent())
		{
			Rating rating = ratingOptional.get();
			rating.setRatingScore(ratingScore);
			rating.setComment(comment);
			return ratingMapper.toDto(ratingRepository.save(rating));
		}
		else
			throw new ResourceNotFoundException("Rating not found");
	}
	
	@Override
	public void delete(int id) {
		ratingRepository.deleteById(id);
	}
	
	@Override
	public List<RatingDTO> findAll(){
		return ratingMapper.toListDto(ratingRepository.findAll());
	}
	
	@Override
	public List<RatingDTO> findByUserAndRestaurant(User user, Restaurant restaurant){
		return ratingMapper.toListDto(ratingRepository.findByUserAndRestaurant(user, restaurant)) ;
	}
	
	@Override
	public List<RatingDTO> findByUser(User user){
		return ratingMapper.toListDto(ratingRepository.findByUser(user));
	}
	
	@Override
	public List<RatingDTO> findByRestaurant(Restaurant restaurant){
		return ratingMapper.toListDto(ratingRepository.findByRestaurant(restaurant));
	}
	

}
