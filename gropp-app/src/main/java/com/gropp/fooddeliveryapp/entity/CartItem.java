package com.gropp.fooddeliveryapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import lombok.Data;

@Entity
@Table(name = "Cart_Items")


@Data
public class CartItem {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;
	@ManyToOne
	@JoinColumn(name = "menu_id", referencedColumnName = "id")
	private Menu menu;
	
	@Max(value = 50, message = "Maximum quantity of an item must be less than or equal to 50")
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = false)
	private double total;
	
	
}
