package com.gropp.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gropp.fooddeliveryapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {


}
