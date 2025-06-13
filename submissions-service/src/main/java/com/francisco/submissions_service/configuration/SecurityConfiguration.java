package com.francisco.submissions_service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
		.csrf(csrf -> csrf.disable())
		 .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/public/**").permitAll()
	                .anyRequest().authenticated()
	            )
	            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) 
	            .build();
	
	}
}
