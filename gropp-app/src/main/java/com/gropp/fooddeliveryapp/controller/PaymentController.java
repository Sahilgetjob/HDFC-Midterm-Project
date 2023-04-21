package com.gropp.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gropp.fooddeliveryapp.DTO.PaymentDTO;
import com.gropp.fooddeliveryapp.entity.Order;
import com.gropp.fooddeliveryapp.service.OrderService;
import com.gropp.fooddeliveryapp.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/findAll")
	public List<PaymentDTO> findAll(){
		return paymentService.findAll();
	}
	
	@GetMapping("/findPaymentById/{paymentId}")
	public PaymentDTO findPaymentById(@PathVariable int paymentId) {
		return paymentService.findPaymentById(paymentId);
	}
	
	@GetMapping("/findPaymentForOrder/{orderId}")
	public PaymentDTO findPaymentForOrder(@PathVariable int orderId) {
		Order order = orderService.getOrder(orderId);
		return paymentService.findPaymentForOrder(order);
	}
}
