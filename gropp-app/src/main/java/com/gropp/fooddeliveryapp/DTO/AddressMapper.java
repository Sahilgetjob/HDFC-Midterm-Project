package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	AddressDTO toDto(Address address);
	Address toEntity(AddressDTO addressDto);
	List<AddressDTO> toListDto(List<Address> addresses); 
}
