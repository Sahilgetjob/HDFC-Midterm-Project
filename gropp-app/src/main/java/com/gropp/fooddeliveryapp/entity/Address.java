package com.gropp.fooddeliveryapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = " User_Addresses")

@Data
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id",
				referencedColumnName = "id")
	private User user;
	
	@NotBlank(message = "State must be provided!")
	@Size(min = 3, max = 30, message = "State name must be between 3 and 30 characters long!")
	private String state;
	
	@NotBlank(message = "City must be provided!")
	@Size(min = 3, max = 30, message = "City name must be between 3 and 30 characters long!")
	private String city;
	
	@NotBlank(message = "Street must be provided!")
	@Size(min = 10, max = 60, message = "Street name must be between 10 and 60 characters long!")
	private String street;
	
	@Pattern(regexp = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$", message = "Invalid PIN code")
	private String pincode;
	
	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();
	
	

}
