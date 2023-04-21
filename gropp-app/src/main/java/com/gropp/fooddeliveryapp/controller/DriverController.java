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

import com.gropp.fooddeliveryapp.DTO.DriverDTO;
import com.gropp.fooddeliveryapp.repository.DriverRepository;
import com.gropp.fooddeliveryapp.service.DriverService;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@PostMapping("/addDriver/{driverName}/{phone}/{vehicleNumber}")
	public DriverDTO createDriver(@PathVariable String driverName, @PathVariable String phone, @PathVariable String vehicleNumber ) {
		return driverService.createDriver(driverName, phone, vehicleNumber);
	}
	@PutMapping("/updateDriver/{driverId}/{driverName}/{phone}/{vehicleNumber}")
	public DriverDTO updateDriver(@PathVariable int driverId, @PathVariable String driverName, 
			@PathVariable String phone, @PathVariable String vehicleNumber) 
	{
		return driverService.updateDriver(driverId, driverName, phone, vehicleNumber);
	}
	
	@DeleteMapping("/deleteDriverById/{driverId}")
	public ResponseEntity<String> deleteDriverById(@PathVariable int driverId){
		driverService.deleteDriverById(driverId);
		if(driverRepository.findById(driverId).isPresent())
			return ResponseEntity.ok("Driver deleted");
		else
			return ResponseEntity.badRequest().body("Driver doesn't exist");
	}
	
	@GetMapping("/findAll")
	public List<DriverDTO> findAll(){
		return driverService.findAll();
	}
	
	@GetMapping("/findDriverById/{driverId}")
	public DriverDTO findDriverById(@PathVariable int driverId) {
		return driverService.findDriverById(driverId);
	}
	
	
}
