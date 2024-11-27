package com.packt.cardatabase.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String password;

	@Column(nullable = false, unique = true)
	private String role;

	public AppUser() {}

	public AppUser(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

}
