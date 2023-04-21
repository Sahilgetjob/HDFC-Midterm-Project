package com.gropp.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gropp.fooddeliveryapp.entity.Order;
import com.gropp.fooddeliveryapp.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Payment findByOrder(Order order);
}
