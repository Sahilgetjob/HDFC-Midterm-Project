package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import lombok.Data;


@Data
public class CartDTO {

	private int id;
	private UserDTO user;
	private RestaurantDTO restaurant;
	private double cartTotal;
	private List<CartItemDTO> cartItems;
}
