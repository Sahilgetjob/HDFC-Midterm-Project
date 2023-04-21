package com.gropp.fooddeliveryapp.DTO;

import lombok.Data;


@Data
public class OrderItemDTO {
	
	private int id;
	private String menuName;
	private double price;
	private int quantity;
	private double total;

}
