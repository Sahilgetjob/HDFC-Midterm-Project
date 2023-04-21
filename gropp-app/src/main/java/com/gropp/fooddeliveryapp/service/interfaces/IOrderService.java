package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.DTO.OrderDTO;
import com.gropp.fooddeliveryapp.entity.Driver;
import com.gropp.fooddeliveryapp.entity.Order;
import com.gropp.fooddeliveryapp.entity.Restaurant;
import com.gropp.fooddeliveryapp.entity.User;
import com.gropp.fooddeliveryapp.entity.enums.PaymentMethod;

public interface IOrderService {

	Order getOrder(int orderId);

	OrderDTO createOrder(int userId, int restaurantId, int addressId);

	OrderDTO addPaymentForOrder(int orderId, PaymentMethod paymentMethod);

	OrderDTO addDriverForOrder(int orderId);

	OrderDTO addRatingForOrder(int orderId, double ratingScore, String comment);

	OrderDTO findOrderById(int orderId);
	
	List<OrderDTO> findAll();

	List<OrderDTO> findByUser(User user);

	List<OrderDTO> findByRestaurant(Restaurant restaurant);

	List<OrderDTO> findByUserAndRstaurant(User user, Restaurant restaurant);

	List<OrderDTO> findByDriver(Driver driver);


}
