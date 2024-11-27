package com.packt.cardatabase.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.packt.cardatabase.domain.AppUser;
import com.packt.cardatabase.domain.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AppUserRepository userRepo;

	public UserDetailsServiceImpl(AppUserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
							throws UsernameNotFoundException {
		
		Optional<AppUser> user = userRepo.findByUsername(username);
		
		if(user.isPresent()) {
			
			AppUser currentUser = user.get();
			
			UserBuilder builder = User
					.withUsername(username)
					.password(currentUser.getPassword())
					.roles(currentUser.getRole());
					
			return builder.build();
		}
		else
			throw new UsernameNotFoundException("User " + username + " not found");
		
	}

}
