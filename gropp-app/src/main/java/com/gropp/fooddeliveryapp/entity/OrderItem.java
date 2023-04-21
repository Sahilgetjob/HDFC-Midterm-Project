package com.gropp.fooddeliveryapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Order_Items")


@Data
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@ManyToOne
	@JoinColumn(name="order_id",
				referencedColumnName = "id")
	private  Order order;
	
	@Column(nullable = false)
	private String menuName;
	
	@Column(nullable = false)
	private double price;
	
	
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = false)
	private double total;
}
