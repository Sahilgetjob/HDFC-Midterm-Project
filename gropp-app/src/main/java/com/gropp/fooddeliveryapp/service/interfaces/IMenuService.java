package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.DTO.MenuDTO;
import com.gropp.fooddeliveryapp.entity.Menu;
import com.gropp.fooddeliveryapp.entity.Restaurant;

public interface IMenuService {

	Menu getMenu(int menuId);

	void delete(Menu menu);

	Menu save(Menu menu);

	void addRestaurantMenu(int restaurantId, String itemName, double price);

	void updateRestaurantMenu(int menuId, String itemName, double price);

	void removeRestaurantMenu(int restaurantId, int menuId);

	List<MenuDTO> findAll();

	MenuDTO findByMenuId(int menuId);

	List<MenuDTO> findByRestaurant(Restaurant restaurant);

}
