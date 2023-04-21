package com.gropp.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gropp.fooddeliveryapp.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

}
