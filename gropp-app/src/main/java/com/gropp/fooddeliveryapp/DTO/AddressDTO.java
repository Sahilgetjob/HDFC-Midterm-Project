package com.gropp.fooddeliveryapp.DTO;

import lombok.Data;


@Data
public class AddressDTO {
	
	private int id;
	private String state;
	private String city;
	private String street;
	private String pincode;

}
