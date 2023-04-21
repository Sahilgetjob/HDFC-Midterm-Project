package com.gropp.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.DTO.AddressDTO;
import com.gropp.fooddeliveryapp.DTO.AddressMapper;
import com.gropp.fooddeliveryapp.entity.Address;
import com.gropp.fooddeliveryapp.entity.User;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.AddressRepository;
import com.gropp.fooddeliveryapp.repository.UserRepository;
import com.gropp.fooddeliveryapp.service.interfaces.IAddressService;
import com.gropp.fooddeliveryapp.service.interfaces.IUserService;

@Service
public class AddressService implements IAddressService{

	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressMapper addressMapper;
	
	
	@Override
	public Address getAddress(int addressId) {
		return addressRepository.findById(addressId)
				  .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
	}
	
	@Override
	public void delete(Address address) {
		addressRepository.delete(address);
	}
	
	@Override
	public AddressDTO addUserAddress(int userId, String state, String city, String street, String pincode) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		Address address = new Address();
		address.setUser(user);
		address.setState(state);
		address.setCity(city);
		address.setStreet(street);
		address.setPincode(pincode);
		
		return addressMapper.toDto(addressRepository.save(address));
	}
	
	
	@Override
	public AddressDTO updateUserAddress (int addressId, String state, String city, String street, String pincode) {
		Optional<Address> addressOptional = addressRepository.findById(addressId);
		
		if(addressOptional.isPresent())
		{
			Address address = addressOptional.get();
			address.setState(state);
			address.setCity(city);
			address.setStreet(street);
			address.setPincode(pincode);
			
			return addressMapper.toDto(addressRepository.save(address));
		}
		
		throw new ResourceNotFoundException("Address not found");
	}
	
	@Override
	public void removeUserAddress(int userId, int addressId) {
		User user = userService.getUser(userId);
		Address address = getAddress(addressId);
		user.getAddresses().remove(address);
		addressRepository.deleteById(addressId);
		userRepository.save(user);
		
	}
	
	
	@Override
	public List<AddressDTO> findAll(){
		return addressMapper.toListDto(addressRepository.findAll());
	}
	
	@Override
	public AddressDTO findByAddressId(int addressId) {
		Address address = getAddress(addressId);
		return addressMapper.toDto(address);
	}
	
	@Override
	public List<AddressDTO> findByUserId(int userId){
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
		return addressMapper.toListDto(addressRepository.findByUser(user));
	}
}
