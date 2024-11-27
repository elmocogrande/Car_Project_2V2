package com.packt.cardatabase.service;

import java.security.Key;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtService {
	
	static final long EXPIRATIONTIME= 86400000; // 1 day in milli's
	static final String PREFIX = "Bearer";
	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	// Get a token from request Authorization header,
	// verify the token, and then extract username
	public String getAuthUser(HttpServletRequest request) {
		
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		String user = null;
		
		if(token != null) {
			user = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody()
					.getSubject();
		}
		
		return user;
	}
	
	// Generate a signed JWT token
	public String getToken(String username) {
		
		String token = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(key)
				.compact();
		
		return token;
	}
}
