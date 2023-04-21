package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.DTO.UserDTO;
import com.gropp.fooddeliveryapp.entity.User;

public interface IUserService {

	User getUser(int userId);

	void delete(User user);

	UserDTO save(String name, String email, String password, String phone);

	UserDTO updateUser(int userId, String name, String email, String password, String phone);

	void deleteUserById(int userId);

	List<UserDTO> findAll();

	UserDTO findByUserId(int userId);

}
