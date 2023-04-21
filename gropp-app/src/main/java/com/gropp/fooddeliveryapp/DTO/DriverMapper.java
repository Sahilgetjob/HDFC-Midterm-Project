package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.Driver;

@Mapper(componentModel = "spring")
public interface DriverMapper {

	DriverDTO toDto(Driver driver);
	Driver toEntity(DriverDTO driverDto);
	List<DriverDTO> toListDto(List<Driver> drivers);
}
