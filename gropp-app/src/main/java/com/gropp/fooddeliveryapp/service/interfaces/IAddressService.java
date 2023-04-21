package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.DTO.AddressDTO;
import com.gropp.fooddeliveryapp.entity.Address;

public interface IAddressService {

	Address getAddress(int addressId);

	void delete(Address address);

	AddressDTO addUserAddress(int userId, String state, String city, String street, String pincode);

	void removeUserAddress(int userId, int addressId);

	AddressDTO updateUserAddress(int addressId, String state, String city, String street, String pincode);

	List<AddressDTO> findAll();

	AddressDTO findByAddressId(int addressId);

	List<AddressDTO> findByUserId(int userId);

}
