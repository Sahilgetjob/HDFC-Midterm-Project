package com.gropp.fooddeliveryapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

import com.gropp.fooddeliveryapp.DTO.AddressDTO;
import com.gropp.fooddeliveryapp.service.AddressService;

@SpringBootTest
public class AddressServiceTest {

	private static AddressService addressService;
	
	@BeforeAll
	public static void beforeAll() {
		addressService = mock(AddressService.class);
	}
	
	
	void addressServiceAddUserAddressTest() {
		AddressDTO address = new AddressDTO();
		when(addressService.addUserAddress(0, null, null, null, null)).thenReturn(address);
		assertEquals(address, addressService.addUserAddress(0, null, null, null, null));
	}
	
	
	void addressServiceUpdateUserAddressTest() {
		AddressDTO address = new AddressDTO();
		when(addressService.updateUserAddress(0, null, null, null, null)).thenReturn(address);
		assertEquals(address, addressService.updateUserAddress(0, null, null, null, null));
	}
	
	void addressServiceFindAllTest() {
		List<AddressDTO> addresses = List.of(new AddressDTO());
		when(addressService.findAll()).thenReturn(addresses);
		assertEquals(addresses, addressService.findAll());
	}
	
	void addressServiceFindByAddressIdTest() {
		AddressDTO address = new AddressDTO();
		when(addressService.findByAddressId(0)).thenReturn(address);
		assertEquals(address, addressService.findByAddressId(0));
	}
}
