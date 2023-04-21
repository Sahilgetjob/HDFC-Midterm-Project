package com.gropp.fooddeliveryapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.DTO.PaymentDTO;
import com.gropp.fooddeliveryapp.DTO.PaymentMapper;
import com.gropp.fooddeliveryapp.entity.Order;
import com.gropp.fooddeliveryapp.entity.Payment;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.PaymentRepository;
import com.gropp.fooddeliveryapp.service.interfaces.IPaymentService;

@Service
public class PaymentService implements IPaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	

	
	@Autowired
	private PaymentMapper paymentMapper;
	
	@Override
	public PaymentDTO findPaymentForOrder(Order order) {
		return paymentMapper.toDto(paymentRepository.findByOrder(order));
	}
	
	@Override
	public List<PaymentDTO> findAll(){
		return paymentMapper.toListDto(paymentRepository.findAll());
	}
	
	@Override
	public PaymentDTO findPaymentById(int paymentId) {
		Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
		return paymentMapper.toDto(payment);
	}
}
