package com.gropp.fooddeliveryapp.DTO;

import com.gropp.fooddeliveryapp.entity.enums.PaymentMethod;
import com.gropp.fooddeliveryapp.entity.enums.PaymentStatus;

import lombok.Data;


@Data
public class PaymentDTO {

	private int id;
	private PaymentMethod paymentMethod;
	private String transactionId;
	private PaymentStatus paymentStatus;
	private double amount;
}
