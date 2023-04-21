package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.Menu;

@Mapper(componentModel = "spring")
public interface MenuMapper {

	MenuDTO toDto(Menu menu);
	Menu toEntity(MenuDTO menuDto);
	List<MenuDTO> toListDto(List<Menu> menues);
}
