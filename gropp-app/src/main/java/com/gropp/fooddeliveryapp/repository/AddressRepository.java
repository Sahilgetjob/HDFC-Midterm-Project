package com.gropp.fooddeliveryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gropp.fooddeliveryapp.entity.Address;
import com.gropp.fooddeliveryapp.entity.User;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByUser(User user);
}
