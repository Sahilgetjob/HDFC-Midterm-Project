package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

	RestaurantDTO toDto(Restaurant restaurant);
	Restaurant toEntity(RestaurantDTO restaurantDto);
	List<RestaurantDTO> toListDto(List<Restaurant> restaurants);
}
