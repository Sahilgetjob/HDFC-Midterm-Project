package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.DTO.PaymentDTO;
import com.gropp.fooddeliveryapp.entity.Order;

public interface IPaymentService {

	PaymentDTO findPaymentForOrder(Order order);

	List<PaymentDTO> findAll();

	PaymentDTO findPaymentById(int paymentId);

}
