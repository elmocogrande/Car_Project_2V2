package com.packt.cardatabase;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.packt.cardatabase.service.JwtService;

import java.util.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Authentication Filter
 * 
 * Description: Handles the authentication for incoming requests, except for requests to login.
 * 
 * By using filters we can perform some operations before a request goes to the controller or
 * before the response is sent to a client.
 */

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	
	public AuthenticationFilter(JwtService jwtService) {
		this.jwtService=jwtService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
						throws ServletException, IOException {
		
		// Get token from the authorization header
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(token != null ) {
			// Verify token and get user
			String user = jwtService.getAuthUser(request);
			
			// Authenticate
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					user, null, Collections.emptyList());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		
	}
	

}
