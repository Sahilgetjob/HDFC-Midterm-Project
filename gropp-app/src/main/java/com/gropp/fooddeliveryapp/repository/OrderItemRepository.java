package com.gropp.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gropp.fooddeliveryapp.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
