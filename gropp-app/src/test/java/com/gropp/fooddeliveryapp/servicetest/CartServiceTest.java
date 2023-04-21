package com.gropp.fooddeliveryapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gropp.fooddeliveryapp.DTO.CartDTO;
import com.gropp.fooddeliveryapp.service.CartService;

@SpringBootTest
public class CartServiceTest {
	
	private static CartService cartService;
	
	@BeforeAll
	public static void beforeAll() {
		cartService = mock(CartService.class);
	}
	
	@Test
	public void cartServiceFindCartByIdTest() {
		CartDTO cart = new CartDTO();
		when(cartService.findCartById(0)).thenReturn(cart);
		assertEquals(cart, cartService.findCartById(0));
	}

	@Test
	public void cartServiceFindAllTest() {
		List<CartDTO> carts = List.of(new CartDTO());
		when(cartService.findAll()).thenReturn(carts);
		assertEquals(carts, cartService.findAll());
	}
	
	@Test
	public void cartServiceFindByUserIdAndRestaurantIdTest() {
		CartDTO cart = new CartDTO();
		when(cartService.findByUserIdAndRestaurantId(0, 0)).thenReturn(cart);
		assertEquals(cart, cartService.findByUserIdAndRestaurantId(0, 0));
	}
}
