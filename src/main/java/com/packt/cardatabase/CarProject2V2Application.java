package com.packt.cardatabase;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.packt.cardatabase.domain.AppUser;
import com.packt.cardatabase.domain.AppUserRepository;
import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;

@SpringBootApplication
public class CarProject2V2Application implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(CarProject2V2Application.class);
	private final CarRepository carRepo;
	private final OwnerRepository ownerRepo;
	private final AppUserRepository appUserRepo;
	
	// Constructor
	public CarProject2V2Application(CarRepository carRepo, 
									OwnerRepository ownerRepo,
									AppUserRepository appUserRepo){
		this.carRepo = carRepo;
		this.ownerRepo = ownerRepo;
		this.appUserRepo = appUserRepo;
	}
	
	@Override
	public void run(String... args) {
		
		
//		// Save some sample owners to the db
//		Owner dan = new Owner("Dan", "Kopp");
//		Owner aidan = new Owner("Aidan", "Kopp");
//		ownerRepo.saveAll(List.of(dan,aidan)); 
//		
//		
//		// Save some sample cars to the db
//		carRepo.save(new Car("Ford", "Mustang", "Red","ADF-1121", 2023, 59000, dan ));
//		carRepo.save(new Car("Chevy", "Impala", "Tan","ADF-1121", 2024, 59000, dan ));
//		carRepo.save(new Car("Buick", "LaCrosse", "Green","ADF-1121", 2025, 59000, aidan ));
		
		// Same some users to the database
//		AppUser regularUser = new AppUser("user", "$2a$12$qo.BQDPWqbNjaqkhts1Fxe.zM21dVaEK4STe2JtbvNpd8.DRJypjm", "USER");
//		AppUser adminUser = new AppUser("admin", "$2a$12$RgDqY69pZCw28vdm2Vwx3OV.iOQHBAmdcq8YkoMnCkC8qSLg.g/7K", "ADMIN");
//		appUserRepo.saveAll(List.of(regularUser,adminUser));
		
		// Retrieve all cars from the db and log to the console
//		for(Car c : carRepo.findAll()) {
//			logger.info("make {}, model {}", c.getBrand(), c.getModel());
//		}
//		
//		// Retrieve all owners from the db and log to the console
//		for(Owner o : ownerRepo.findAll()) {
//			logger.info("{} {}", o.getFirstname(), o.getLastname());
//		}
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CarProject2V2Application.class, args);
	}

}
