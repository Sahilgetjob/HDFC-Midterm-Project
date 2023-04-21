package com.gropp.fooddeliveryapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gropp.fooddeliveryapp.DTO.UserDTO;
import com.gropp.fooddeliveryapp.service.UserService;

@SpringBootTest
public class UserServiceTest {
	
	
	private static UserService userService;
	
	@BeforeAll
	public static void beforeAll() {
		userService = mock(UserService.class);
	}
	
	@Test
	void userServiceSaveTest() {
		UserDTO user = new UserDTO();
		when(userService.save(null, null, null, null)).thenReturn(user);
		assertEquals(user, userService.save(null, null, null, null));
	}
	
	@Test
	void userServiceUpdateTest() {
		UserDTO user = new UserDTO();
		when(userService.updateUser(0, null, null, null, null)).thenReturn(user);
		assertEquals(user, userService.updateUser(0, null, null, null, null));
	}
	@Test
	void userServiceFindTest() {
		UserDTO user = new UserDTO();
		when(userService.findByUserId(0)).thenReturn(user);
		
		assertEquals(user, userService.findByUserId(0));
		
	}
	
	@Test
	void userServiceFindAllTest() {
		List<UserDTO> users = List.of(new UserDTO(), new UserDTO(), new UserDTO());
		when(userService.findAll()).thenReturn(users);
		assertEquals(users, userService.findAll());
	}

}
