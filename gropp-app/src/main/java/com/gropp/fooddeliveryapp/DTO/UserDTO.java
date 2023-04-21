package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import lombok.Data;


@Data
public class UserDTO {

	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private List<AddressDTO> addresses;
}
