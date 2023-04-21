package com.gropp.fooddeliveryapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.springframework.lang.Nullable;

import lombok.Data;

@Entity
@Table(name="Restaurants")


@Data
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name is required!")
	@Size(min = 5, max = 30, message = "Name must be atleast 5 and atmost 30 characrters long")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "Restaurant Address is required!")
	@Size(min = 10, max = 50, message = "Restaurant Address must be atleast 10 and atmost 50 characrters long")
	@Column(nullable = false)
	private String address;
	
	@NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
	@Column(nullable = false)
	private String phone;

	@Nullable
	@Size(max = 50, message = "Description must be atleast 0 and atmost 50 characrters long")
	private String description;
	
	@NotBlank(message = "Cuisine type must be provided!")
	@Size(min = 5, max = 30, message = "Cuisine type must be atleast 5 and atmost 30 characrters long")
	@Column(nullable = false)
	private String cuisineType;
	
	@OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();
	
	@OneToMany(mappedBy = "restaurant", cascade =CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Menu> menuItems = new ArrayList<>();
	
	@OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Rating> ratings = new ArrayList<>();
	
	
}
