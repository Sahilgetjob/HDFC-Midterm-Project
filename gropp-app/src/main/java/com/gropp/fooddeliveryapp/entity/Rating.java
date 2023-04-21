package com.gropp.fooddeliveryapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import lombok.Data;

@Entity
@Table(name="User_Ratings")


@Data
public class Rating {

//	In this schema, we have added a new Ratings table with a primary key rating_id.
//	The table has foreign key constraints with user_id referencing User table,
//	restaurant_id referencing Restaurant table, and order_id referencing Order table. 
//	This ensures that a rating is associated with a specific user, restaurant, and order.
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private Restaurant restaurant;
	
	@OneToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;
	
	@Max(value = 5, message = "Value must be less than or equal to 5")
	private double ratingScore;
	
	@Nullable
	@Size(max = 60, message = "Comment must be atleast 0 and atmost 60 characrters long")
	private String comment;
}
