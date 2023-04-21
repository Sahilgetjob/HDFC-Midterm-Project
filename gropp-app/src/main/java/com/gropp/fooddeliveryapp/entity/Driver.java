	package com.gropp.fooddeliveryapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name ="deliver_guy")


@Data
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name is required!")
	@Size(min = 5, max = 30, message = "Driver name must be atleast 5 and atmost 30 characrters long")
	private String name;
	
	@NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
	private String phone;
	
	@Pattern(regexp = "^([A-Z]{2}\\s\\d{2}\\s[A-Z]{2}\\s\\d{4})?$", message = "Invalid vehicle number format - 'AA 11 AA 1111")
	private String vehicleNumber;
	
	@OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

}
