package com.gropp.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gropp.fooddeliveryapp.DTO.OrderItemDTO;
import com.gropp.fooddeliveryapp.service.OrderItemService;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping("/findAll")
	public List<OrderItemDTO> findAll(){
		return orderItemService.findAll();
	}
	
	@GetMapping("/findOrderItemById/{orderItemId}")
	public OrderItemDTO findOrderItemById(@PathVariable int orderItemId) {
		return orderItemService.findOrderItemById(orderItemId);
	}
}
