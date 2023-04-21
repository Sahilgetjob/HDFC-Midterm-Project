package com.gropp.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.DTO.RestaurantDTO;
import com.gropp.fooddeliveryapp.DTO.RestaurantMapper;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.RestaurantRepository;
import com.gropp.fooddeliveryapp.service.interfaces.IRestaurantService;

@Service
public class RestaurantService implements IRestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantMapper restaurantMapper;
	
	@Override
	public Restaurant getRestaurant(int restaurantId) {
		return restaurantRepository.findById(restaurantId)
								   .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
	}
	
	@Override
	public void save(Restaurant restaurant) {
		restaurantRepository.save(restaurant);		
	}
	
	@Override
	public RestaurantDTO createRestaurant(String name, String address, String phone, String description, String cuisineType ) {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(name);
		restaurant.setAddress(address);
		restaurant.setPhone(phone);
		restaurant.setDescription(description);
		restaurant.setCuisineType(cuisineType);
		
		return restaurantMapper.toDto(restaurantRepository.save(restaurant));
	}
	
	
	@Override
	public RestaurantDTO updateRestaurant(int restaurantId, String name, String address, String phone, String description, String cuisineType) {
		Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
		if(restaurantOptional.isPresent())
		{
			Restaurant restaurant = restaurantOptional.get();
			restaurant.setName(name);
			restaurant.setAddress(phone);
			restaurant.setPhone(address);
			restaurant.setDescription(description);
			restaurant.setCuisineType(cuisineType);
			
			return restaurantMapper.toDto(restaurantRepository.save(restaurant));
		}
		else
			throw new ResourceNotFoundException("Restaurant not found to update");
	}
	
	
	@Override
	public List<RestaurantDTO> findAll(){
		return restaurantMapper.toListDto(restaurantRepository.findAll());
	}
	
	@Override
	public RestaurantDTO findByResturantId(int restaurantId) {
		Restaurant restaurant = getRestaurant(restaurantId);
		return restaurantMapper.toDto(restaurant);
	}
	
	
	@Override
	public void deleteByRestaurantId(int restaurantId){
		restaurantRepository.deleteById(restaurantId);
	}
}
