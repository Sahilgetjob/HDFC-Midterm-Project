package com.gropp.fooddeliveryapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.entity.Cart;
import com.gropp.fooddeliveryapp.entity.CartItem;
import com.gropp.fooddeliveryapp.entity.Menu;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.CartItemRepository;
import com.gropp.fooddeliveryapp.service.interfaces.ICartItemService;
import com.gropp.fooddeliveryapp.service.interfaces.IMenuService;

@Service
public class CartItemService implements ICartItemService{
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	
	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	
	@Override
	public CartItem getCartItem(int cartItemId) {
		return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart Item not found"));
	}
	
	@Override
	public List<CartItem> findByMenu(int menuId){
		Menu menu = menuService.getMenu(menuId);
		return cartItemRepository.findByMenu(menu);
	}
	
	@Override
	public List<CartItem> findAll(){
		return cartItemRepository.findAll();
	}
	
	@Override
	public void deleteById(int cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}
	
	@Override
	public void deleteByCart(Cart cart) {
		cartItemRepository.deleteByCart(cart);
	}
	
	@Override
	public void delete(CartItem cartItem) {
		cartItemRepository.delete(cartItem);
	}
	
}
