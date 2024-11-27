package com.packt.cardatabase;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.packt.cardatabase.service.UserDetailsServiceImpl;

/** 
 * SecuritConfig
 * 
 * Description: We'll set our own UserDetailsService, not the
 * in-memory, which is the Spring Security default.
 * Note: by setting passwordEncoder() here, we're
 * telling the AuthenticationManager that passwords
 * coming from the source (database in our case) are
 * encrypted and therefore need to be de-crypted and
 * then compared to the password the user typed in the 
 * web application. */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final UserDetailsServiceImpl userDetailsService;
	private final AuthenticationFilter authenticationFilter;
	private final AuthEntryPoint exceptionHandler;
	
	// Constructor
	public SecurityConfig(UserDetailsServiceImpl userDetailsService, 
			AuthenticationFilter authenticationFilter,
			AuthEntryPoint exceptionHandler) {
		this.userDetailsService=userDetailsService;
		this.authenticationFilter=authenticationFilter;
		this.exceptionHandler=exceptionHandler;
	}
		
	public void configureGlobal(AuthenticationManagerBuilder authMgr)
									throws Exception {
		authMgr.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig)
    								throws Exception {
    	return authConfig.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http.csrf(csrf -> csrf.disable())
    		.cors(Customizer.withDefaults())
    		.sessionManagement( sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests( authHttpReq -> 
						authHttpReq
							.requestMatchers(HttpMethod.POST, "/login").permitAll()
							.anyRequest().authenticated())
			.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling( e -> e.authenticationEntryPoint(exceptionHandler));
    	
    	return http.build(); 

    }
    
    // Cross Origin Resource Sharing (CORS). Since this site will be accessed by a 
    // React front-end, we'll allow requests from a different origin.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	CorsConfiguration config = new CorsConfiguration();
    	
	    	config.setAllowedOrigins(Arrays.asList("*"));
//	    	config.setAllowedOrigins(Arrays.asList("http://www.powerup.com:8080"));
	    	config.setAllowedMethods(Arrays.asList("*"));
	    	config.setAllowedHeaders(Arrays.asList("*"));
	    	config.setAllowCredentials(false);
	    	config.applyPermitDefaultValues();
	    	
	    	source.registerCorsConfiguration("/**", config);
    	
    	return source; 	
    	
    }
      
}

