package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.Rating;

@Mapper(componentModel = "spring")
public interface RatingMapper {
	
	RatingDTO toDto(Rating rating);
	Rating toEntity(RatingDTO ratingDto);
	List<RatingDTO> toListDto(List<Rating> ratings);

}
