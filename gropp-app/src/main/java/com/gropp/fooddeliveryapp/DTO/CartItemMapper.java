package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.CartItem;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

	CartItemDTO toDto(CartItem cartItem);
	CartItem toEntity(CartItemDTO cartItemDto);
	List<CartItemDTO> toListDto(List<CartItem> cartItems);
}
