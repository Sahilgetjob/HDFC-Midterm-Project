package com.gropp.fooddeliveryapp.service.interfaces;

import java.util.List;

import com.gropp.fooddeliveryapp.DTO.DriverDTO;
import com.gropp.fooddeliveryapp.entity.Driver;

public interface IDriverService {

	Driver getDriver(int driverId);

	DriverDTO createDriver(String driverName, String phone, String vehicleNumber);
	
	List<DriverDTO> findAll();

	DriverDTO findDriverById(int driverId);

	DriverDTO updateDriver(int driverId, String driverName, String phone, String vehicleNumber);

	void deleteDriverById(int driverId);



}
