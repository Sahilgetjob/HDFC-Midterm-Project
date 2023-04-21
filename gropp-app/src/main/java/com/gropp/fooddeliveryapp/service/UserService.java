package com.gropp.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.DTO.UserDTO;
import com.gropp.fooddeliveryapp.DTO.UserMapper;
import com.gropp.fooddeliveryapp.entity.User;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.UserRepository;
import com.gropp.fooddeliveryapp.service.interfaces.IUserService;


@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUser(int userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}
	
	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	@Override
	public UserDTO save(String name, String email, String password, String phone) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		
		return userMapper.toDto(userRepository.save(user));
	}
	
	@Override
	public UserDTO updateUser(int userId, String name, String email, String password, String phone) {
		Optional<User> userOptional = userRepository.findById(userId);
		if(userOptional.isPresent())
		{
			User user = userOptional.get();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhone(phone);
			return userMapper.toDto(userRepository.save(user));
		}
		else
			throw new ResourceNotFoundException("User not found to update");	
		
	}
	
	@Override
	public void deleteUserById(int userId) {
		userRepository.deleteById(userId);
	}
	
	
	@Override
	public List<UserDTO> findAll(){
		return userMapper.toListDto(userRepository.findAll());
		
	}
	
	@Override
	public UserDTO findByUserId(int userId) {
		User user = getUser(userId);
		return userMapper.toDto(user);
	}
	
}
