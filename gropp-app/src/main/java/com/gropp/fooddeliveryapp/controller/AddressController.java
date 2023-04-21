package com.gropp.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gropp.fooddeliveryapp.DTO.AddressDTO;
import com.gropp.fooddeliveryapp.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/addUserAddress/{userId}/{state}/{city}/{street}/{pincode}")
	public AddressDTO addUserAddress(@PathVariable int userId, @PathVariable String state, 
											   @PathVariable String city, @PathVariable String street,  @PathVariable String pincode)
	{
		return addressService.addUserAddress(userId, state, city, street, pincode);
	}
	
	
	@PutMapping("/updateUserAddress/{addressId}/{state}/{city}/{street}/{pincode}")
	public AddressDTO updateUserAddress(@PathVariable int addressId, @PathVariable String state, @PathVariable String city, 
			@PathVariable String street, @PathVariable String pincode ) 
	{
		return addressService.updateUserAddress(addressId, state, city, street, pincode);
	}
	
	@DeleteMapping("/removeUserAddress/{userId}/{addressId}")
	public ResponseEntity<String> removeUserAddress(@PathVariable int userId, @PathVariable int addressId){
		addressService.removeUserAddress(userId, addressId);
		return ResponseEntity.ok("Removed");
	}
	
	@GetMapping("/findAll")
	public List<AddressDTO> findAll(){
		return addressService.findAll();
	}
	
	@GetMapping("/findByAddressId/{addressId}")
	public AddressDTO findByAddressId(@PathVariable int addressId) {
		return addressService.findByAddressId(addressId);
	}
	
	@GetMapping("/findByUserId/{userId}")
	public List<AddressDTO> findByUserId(@PathVariable int userId){
		return addressService.findByUserId(userId);
	}
	
	
}
