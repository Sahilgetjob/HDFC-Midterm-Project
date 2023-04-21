package com.gropp.fooddeliveryapp.DTO;

import java.util.List;

import org.mapstruct.Mapper;

import com.gropp.fooddeliveryapp.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
	
	PaymentDTO toDto(Payment payment);
	Payment toEntity(PaymentDTO paymentDto);
	List<PaymentDTO> toListDto(List<Payment> payments);
}
