package com.packt.cardatabase.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Car {
	
	// Have DB generate primary key
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	// Add attributes like name change, nullable, etc.
	@Column(name="make", nullable=false, length=512)
	private String brand;
	
	// Mapped to column names in DB
	private String registrationNumber;
	private int modelYear;
	private int price;
	private String model;
	private String color;
	
	// Add Many-to-One relationship to Owner entity
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="owner")
	private Owner owner;
	
	// For Spring Boot, there must be a default empty constructor
	public Car() {}
	
	// Constructor
	public Car(String brand, String model, String color, String registrationNumber, int modelYear, int price, Owner owner) {
		super();
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.registrationNumber = registrationNumber;
		this.modelYear = modelYear;
		this.price = price;
		this.owner = owner;
	}
	


}
