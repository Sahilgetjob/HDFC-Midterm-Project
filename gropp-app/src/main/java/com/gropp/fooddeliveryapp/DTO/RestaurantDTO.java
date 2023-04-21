package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import lombok.Data;


@Data
public class RestaurantDTO {

	private int id;
	private String name;
	private String address;
	private String phone;
	private String description;
	private String cuisineType;
	private List<MenuDTO> menuItems;
	private List<RatingDTO> ratings;

}
