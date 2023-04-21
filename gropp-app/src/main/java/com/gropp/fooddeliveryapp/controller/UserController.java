package com.gropp.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gropp.fooddeliveryapp.DTO.UserDTO;
import com.gropp.fooddeliveryapp.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/saveUser/{name}/{email}/{password}/{phone}")
	public UserDTO createUser(@PathVariable String name, @PathVariable String email, 
			  			@PathVariable String password, @PathVariable String phone) 
	{
		return userService.save(name, email, password, phone);
	}
	
	@PutMapping("/updateUser/{userId}/{name}/{email}/{password}/{phone}")
	public UserDTO updateUser(@PathVariable int userId, @PathVariable String name, @PathVariable String email, 
						  @PathVariable String password, @PathVariable String phone) 
	{
		return userService.updateUser(userId, name, email, password, phone);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUserById(int userId){
		userService.deleteUserById(userId);
		return ResponseEntity.ok("User deleted");
	}
	
	@GetMapping("/findAll")
	public List<UserDTO> findAll(){
		return userService.findAll();
		
	}
	
	@GetMapping("/findUserById/{userId}")
	public UserDTO findUserById(int userId) {
		return userService.findByUserId(userId);
	}
	

}
