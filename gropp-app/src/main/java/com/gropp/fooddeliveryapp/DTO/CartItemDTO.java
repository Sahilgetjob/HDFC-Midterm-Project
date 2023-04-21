package com.gropp.fooddeliveryapp.DTO;

import lombok.Data;


@Data
public class CartItemDTO {

	private int id;
	private MenuDTO menu;
	private int quantity;
	private double total;
}
