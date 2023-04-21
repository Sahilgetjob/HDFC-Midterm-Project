package com.gropp.fooddeliveryapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import com.gropp.fooddeliveryapp.entity.enums.PaymentMethod;
import com.gropp.fooddeliveryapp.entity.enums.PaymentStatus;

import lombok.Data;

@Entity
@Table(name="Order_Payment")


@Data
public class Payment {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @OneToOne
	    @JoinColumn(name = "order_id", referencedColumnName = "id")
	    private Order order;

	    @Column(nullable = false)
	    private PaymentMethod paymentMethod;
	    
	    @Column(nullable = false)
	    private String transactionId;

	    @Column(nullable = false)
	    private PaymentStatus paymentStatus;
	    
	    @Max(value = 20000, message = "Order amount must not be greater than 20000 Rs.")
	    @Column(nullable = false)
	    private double amount;
	


}
