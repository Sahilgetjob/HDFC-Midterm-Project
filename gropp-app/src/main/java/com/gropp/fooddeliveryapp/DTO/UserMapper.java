package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO toDto(User user);
	User toEntity(UserDTO userDto);
	List<UserDTO> toListDto(List<User> users);
}
