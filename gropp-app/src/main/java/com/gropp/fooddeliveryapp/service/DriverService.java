package com.gropp.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gropp.fooddeliveryapp.DTO.DriverDTO;
import com.gropp.fooddeliveryapp.DTO.DriverMapper;
import com.gropp.fooddeliveryapp.entity.Driver;
import com.gropp.fooddeliveryapp.exceptionhandler.ResourceNotFoundException;
import com.gropp.fooddeliveryapp.repository.DriverRepository;
import com.gropp.fooddeliveryapp.service.interfaces.IDriverService;

@Service
public class DriverService implements IDriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private DriverMapper driverMapper;
	
	
	@Override
	public Driver getDriver(int driverId) {
		return driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
	}
	
	@Override
	public DriverDTO createDriver(String driverName, String phone, String vehicleNumber) {
		Driver driver = new Driver();
		driver.setName(driverName);
		driver.setPhone(phone);
		driver.setVehicleNumber(vehicleNumber);
		
		return driverMapper.toDto(driverRepository.save(driver));
	}
	
	@Override
	public DriverDTO updateDriver(int driverId, String driverName, String phone, String vehicleNumber) {
		Optional<Driver> driverOptional = driverRepository.findById(driverId);
		
		if(driverOptional.isPresent())
		{
			Driver driver = driverOptional.get();
			driver.setName(driverName);
			driver.setPhone(phone);
			driver.setVehicleNumber(vehicleNumber);
			
			return driverMapper.toDto(driver);
		}
		else
			throw new ResourceNotFoundException("Driver not found");
	}
	
	
	@Override
	public void deleteDriverById(int driverId) {
		driverRepository.deleteById(driverId);
	}
	
	@Override
	public List<DriverDTO> findAll(){
		return driverMapper.toListDto(driverRepository.findAll());
	}
	
	@Override
	public DriverDTO findDriverById(int driverId) {
		Driver driver = getDriver(driverId);
		return driverMapper.toDto(driver);
	}
	
	
}
