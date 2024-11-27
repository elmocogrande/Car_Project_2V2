package com.packt.cardatabase.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Owner {
	
	// Primary key column
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ownerid;
	
	// Column names
	private String firstname;
	private String lastname;
	
	// OneToMany relationship to Car entity
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="owner")
	private List<Car> cars;
	
	// Constructors
	public Owner() {}
	
	public Owner(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	

}
