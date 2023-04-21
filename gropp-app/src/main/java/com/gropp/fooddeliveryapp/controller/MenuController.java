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

import com.gropp.fooddeliveryapp.DTO.MenuDTO;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.RestaurantRepository;
import com.gropp.fooddeliveryapp.service.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RestaurantRepository restaurantRepository; 
	
	@PostMapping("/addRestaurantMenu/{restaurantId}/{itemName}/{price}")
	public ResponseEntity<String> addRestaurantMenu(@PathVariable int restaurantId, @PathVariable String itemName, @PathVariable double price){
		 menuService.addRestaurantMenu(restaurantId, itemName, price);
		 return ResponseEntity.ok("Menu Added");
	}
	
	@PutMapping("/updateRestaurantMenu/{menuId}/{itemName}/{price}")
	public ResponseEntity<String> updatRestauranteMenu(@PathVariable int menuId, @PathVariable String itemName, @PathVariable double price) {
		menuService.updateRestaurantMenu(menuId, itemName, price);
		return ResponseEntity.ok("Menu Updated");
	}
	
	@DeleteMapping("/removeRestaurantMenu/{restaurantId}/{menuId}")
	public ResponseEntity<String> removeRestaurantMenu(@PathVariable int restaurantId, @PathVariable int menuId){
		menuService.removeRestaurantMenu(restaurantId, menuId);
		return ResponseEntity.ok("Menu Removed");
	}
	
	
	
	@GetMapping("/findAll")
	public List<MenuDTO> findAll(){
		return menuService.findAll();
	}
	
	@GetMapping("/findByMenuId/{menuId}")
	public MenuDTO findByMenuId(@PathVariable int menuId) {
		return menuService.findByMenuId(menuId);
	}
	
	@GetMapping("/findByRestaurant/{restaurantId}")
	public List<MenuDTO> findByRestaurant(@PathVariable int restaurantId){
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
		
		return menuService.findByRestaurant(restaurant);
	}

}
