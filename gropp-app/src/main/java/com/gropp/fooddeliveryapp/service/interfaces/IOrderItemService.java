package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.DTO.OrderItemDTO;

public interface IOrderItemService {

	List<OrderItemDTO> findAll();

	OrderItemDTO findOrderItemById(int orderItemId);

}
