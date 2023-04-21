package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	OrderDTO toDto(Order order);
	Order toEntity(OrderDTO orderDto);
	List<OrderDTO> toListDto(List<Order> orders);
}
