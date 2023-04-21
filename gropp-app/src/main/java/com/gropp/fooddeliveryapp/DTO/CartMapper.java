package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper {

	CartDTO toDto(Cart cart);
	Cart toEntity(CartDTO cartDto);
	
	List<CartDTO> toListDto(List<Cart> carts);
}
