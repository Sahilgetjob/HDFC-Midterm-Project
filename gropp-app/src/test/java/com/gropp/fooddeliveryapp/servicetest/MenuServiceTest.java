package com.gropp.fooddeliveryapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gropp.fooddeliveryapp.DTO.MenuDTO;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.service.MenuService;

public class MenuServiceTest {

	private static MenuService menuService;
	
	@BeforeAll
	public static void beforeAll() {
		menuService = mock(MenuService.class);
	}
	
	@Test
	void menuServiceFindByMenuTest() {
		MenuDTO menu = new MenuDTO();
		when(menuService.findByMenuId(0)).thenReturn(menu);
		assertEquals(menu, menuService.findByMenuId(0));
	}
	
	@Test
	void menuServiceFindAllTest() {
		List<MenuDTO> menues = List.of(new MenuDTO());
		when(menuService.findAll()).thenReturn(menues);
		assertEquals(menues, menuService.findAll());
	}
	
	@Test
	void menuServiceFindByRestaurant() {
		List<MenuDTO> menues = List.of(new MenuDTO());
		when(menuService.findByRestaurant(new Restaurant())).thenReturn(menues);
		assertEquals(menues, menuService.findByRestaurant(new Restaurant()));
	}
	
}
