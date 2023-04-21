package com.gropp.fooddeliveryapp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gropp.fooddeliveryapp.entity.enums.OrderStatus;

import lombok.Data;

//Even with lazy fetching, if you are using Jackson to serialize your entities,
//it will try to access the lazy-loaded collections to serialize them. 
//Since the transaction is already closed, the lazy collection can't be loaded, 
//resulting in a LazyInitializationException. However, 
//this exception is typically caught and the response is sent back to the client as is,
//without the lazy-loaded collections being serialized.

//To avoid including the empty lazy-loaded collections in your response, 
//you can annotate the collections with @JsonIgnore, which tells Jackson to ignore them during serialization.
//Alternatively, you can use DTOs (Data Transfer Objects) to selectively
//include the data that you want to return in your response. 
//This way, you can load only the required data and avoid loading the entire object graph.

@Entity
@Table(name="User_Orders")


@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	

	@ManyToOne
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private Restaurant restaurant;
	
	private LocalDate orderDate;
	
	private double orderTotal;
	
	

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<OrderItem> orderItems = new ArrayList<>();
	

	@ManyToOne
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Payment payment;
	

	@ManyToOne
	@JoinColumn(name = "driver_id", referencedColumnName = "id")
	private Driver driver;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Rating rating;	
}

