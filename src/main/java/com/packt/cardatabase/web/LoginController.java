package com.packt.cardatabase.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.AccountCredentials;
import com.packt.cardatabase.service.JwtService;

@RestController
public class LoginController {
	
	// Instance Fields
	private final JwtService jwtService;
	private final AuthenticationManager authManager;
	
	// Constructor
	public LoginController(JwtService jwtService, AuthenticationManager authManager) {
		this.jwtService = jwtService;
		this.authManager = authManager;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials){
		
		// Get username & password from request body
		UsernamePasswordAuthenticationToken creds = 
				new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
		
		// Get Authentication using the AuthenticationManager and Credentials
		Authentication auth = authManager.authenticate(creds);
		
		// Generate token using the authentication from previous step
		String jwts = jwtService.getToken(auth.getName());
		
		// Finally, build a response with the generated token
		
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,"Bearer" + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
		 
	}
	
	

}
