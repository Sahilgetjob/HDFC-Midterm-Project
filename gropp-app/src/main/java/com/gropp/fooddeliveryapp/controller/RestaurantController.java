package com.gropp.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gropp.fooddeliveryapp.DTO.RestaurantDTO;
import com.gropp.fooddeliveryapp.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping("/createRestaurant/{name}/{address}/{phone}/{description}/{cuisineType}")
	public RestaurantDTO createRestaurant(@PathVariable String name, @PathVariable String address, 
							  @PathVariable String phone, @PathVariable(required = false) String description, @PathVariable String cuisineType)
	{
		return restaurantService.createRestaurant(name, address, phone, description, cuisineType);
	}
	
	@DeleteMapping("/deleteRestaurant/{restaurantId}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable int restaurantId){
		restaurantService.deleteByRestaurantId(restaurantId);
		return ResponseEntity.ok("Restaurant deleted");
	}
	
	
	@PutMapping("/updateRestaurant/{restaurantId}/{name}/{address}/{phone}/{description}/{cuisineType}")
	public RestaurantDTO update(@PathVariable int restaurantId, @PathVariable String name, @PathVariable String address, 
								@PathVariable String phone, @PathVariable String description, @PathVariable String cuisineType)
	{
		return restaurantService.updateRestaurant(restaurantId, name, address, phone, description, cuisineType);
	}
	
	
	
	@GetMapping("/findAll")
	public List<RestaurantDTO> findAll(){
		return restaurantService.findAll();
	}
	
	@GetMapping("/findRestaurantById/{restaurantId}")
	public RestaurantDTO findRestaurantById(@PathVariable int restaurantId) {
		return restaurantService.findByResturantId(restaurantId);
	}
}
	
