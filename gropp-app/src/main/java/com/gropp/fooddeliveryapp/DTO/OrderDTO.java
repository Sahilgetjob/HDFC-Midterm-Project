package com.gropp.fooddeliveryapp.DTO;

import java.time.LocalDate;
import java.util.List;

import com.gropp.fooddeliveryapp.entity.enums.OrderStatus;

import lombok.Data;


@Data
public class OrderDTO {

	private int id;
	private UserDTO user;
	private RestaurantDTO restaurant;
	private LocalDate orderDate;
	private double orderTotal;
	private OrderStatus orderStatus;
	private PaymentDTO payment;
	private AddressDTO address;
	private DriverDTO driver;
	private List<OrderItemDTO> orderItems;
	private RatingDTO rating;
}
