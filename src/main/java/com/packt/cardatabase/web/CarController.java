package com.packt.cardatabase.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

@RestController
public class CarController {
	
	
	private CarRepository carRepo;
	
	// Constructor
	public CarController(CarRepository carRepo ) {
		this.carRepo = carRepo;
	}
	
	@GetMapping("/cars")
	public Iterable<Car> getCars(){
		return carRepo.findAll();
	}
	
	

}
