package com.gropp.fooddeliveryapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gropp.fooddeliveryapp.DTO.RestaurantDTO;
import com.gropp.fooddeliveryapp.service.RestaurantService;

@SpringBootTest
public class RestaurantServiceTest {

	private static RestaurantService restaurantService;
	
	@BeforeAll
	public static void beforeAll() {
		restaurantService = mock(RestaurantService.class);
	}
	
	@Test
	void restaurantServiceSaveTest() {
		RestaurantDTO restaurant = new RestaurantDTO();
		when(restaurantService.createRestaurant(null, null, null, null, null)).thenReturn(restaurant);
		assertEquals(restaurant, restaurantService.createRestaurant(null, null, null, null, null));
	}
	@Test
	void restaurantServiceUpdateTest() {
		RestaurantDTO restaurant = new RestaurantDTO();
		when(restaurantService.updateRestaurant(0, null, null, null, null, null)).thenReturn(restaurant);
		assertEquals(restaurant, restaurantService.updateRestaurant(0, null, null, null, null, null));
	}
	@Test
	void restaurantServiceFindByIdTest() {
		RestaurantDTO restaurant = new RestaurantDTO();
		when(restaurantService.findByResturantId(0)).thenReturn(restaurant);
		assertEquals(restaurant, restaurantService.findByResturantId(0));
	}
	@Test
	void restaurantServiceFindAllTest() {
		List<RestaurantDTO> restaurants = List.of(new RestaurantDTO(), new RestaurantDTO());
		when(restaurantService.findAll()).thenReturn(restaurants);
		assertEquals(restaurants, restaurantService.findAll());
	}
	
}
