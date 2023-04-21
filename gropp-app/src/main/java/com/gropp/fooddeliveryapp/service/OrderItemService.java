package com.gropp.fooddeliveryapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.DTO.OrderItemDTO;
import com.gropp.fooddeliveryapp.DTO.OrderItemMapper;
import com.gropp.fooddeliveryapp.entity.OrderItem;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.OrderItemRepository;
import com.gropp.fooddeliveryapp.service.interfaces.IOrderItemService;


@Service
public class OrderItemService implements IOrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Override
	public List<OrderItemDTO> findAll(){
		return orderItemMapper.toListDto(orderItemRepository.findAll());
		}
	
	
	@Override
	public OrderItemDTO findOrderItemById(int orderItemId) {
		OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new ResourceNotFoundException("Order Item not found"));
		return orderItemMapper.toDto(orderItem);
	}
}
