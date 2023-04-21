package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

	OrderItemDTO toDto(OrderItem orderItem);
	OrderItem toEntity(OrderItemDTO orderItemDto);
	List<OrderItemDTO> toListDto(List<OrderItem> orderItems);
	
}
