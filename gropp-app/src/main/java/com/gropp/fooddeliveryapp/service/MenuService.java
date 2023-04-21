package com.gropp.fooddeliveryapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.DTO.MenuDTO;
import com.gropp.fooddeliveryapp.DTO.MenuMapper;
import com.gropp.fooddeliveryapp.entity.Cart;
import com.gropp.fooddeliveryapp.entity.CartItem;
import com.gropp.fooddeliveryapp.entity.Menu;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.CartItemRepository;
import com.gropp.fooddeliveryapp.repository.CartRepository;
import com.gropp.fooddeliveryapp.repository.MenuRepository;
import com.gropp.fooddeliveryapp.service.interfaces.IMenuService;
import com.gropp.fooddeliveryapp.service.interfaces.IRestaurantService;

@Service
public class MenuService implements IMenuService {

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private IRestaurantService restaurantService;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	
	@Autowired
	private MenuMapper menuMapper;
	
	
	@Override
	public Menu getMenu(int menuId) {
		return menuRepository.findById(menuId).orElseThrow(() -> new ResourceNotFoundException("Menu not found"));
	}
	
	@Override
	public void delete(Menu menu) {
		menuRepository.delete(menu);
	}
	
	@Override
	public Menu save(Menu menu) {
		return menuRepository.save(menu);
	}
	
	@Override
	public void addRestaurantMenu(int restaurantId, String itemName, double price) {
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		
		Menu menu = new Menu();
		menu.setItemName(itemName);
		menu.setPrice(price);
		menu.setRestaurant(restaurant);	
		save(menu);
		
	}
	
	
	@Override
	public void updateRestaurantMenu(int menuId, String itemName, double price) {
		Menu menu = getMenu(menuId);
		menu.setItemName(itemName);
		menu.setPrice(price);
		save(menu);
		
		for(CartItem cartItem : menu.getCartItems())
		{
			Cart cart = cartItem.getCart();
			cart.setCartTotal(cart.getCartTotal() - cartItem.getTotal());
			
			cartItem.setTotal(cartItem.getQuantity()*price);
			cart.setCartTotal(cart.getCartTotal() + cartItem.getTotal());
			
			cartItemRepository.save(cartItem);
			cartRepository.save(cart);
		}
	}
	
	@Override
	public void removeRestaurantMenu(int restaurantId, int menuId) {
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		Menu menu = getMenu(menuId);
		delete(menu);
		restaurant.getMenuItems().remove(menu);
		restaurantService.save(restaurant);
		for(CartItem cartItem : menu.getCartItems())
		{
			Cart cart = cartItem.getCart();
			cart.setCartTotal(cart.getCartTotal() - cartItem.getTotal());
			cartRepository.save(cart);
		}
	}
		
	
	@Override
	public List<MenuDTO> findAll(){
		return menuMapper.toListDto(menuRepository.findAll());
	}
	
	@Override
	public MenuDTO findByMenuId(int menuId) {
		return menuMapper.toDto(getMenu(menuId));
	}
	
	@Override
	public List<MenuDTO> findByRestaurant(Restaurant restaurant){
		return menuMapper.toListDto(menuRepository.findByRestaurant(restaurant));
	}
	
}
