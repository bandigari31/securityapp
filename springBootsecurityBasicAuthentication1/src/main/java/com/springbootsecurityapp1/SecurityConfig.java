package com.springbootsecurityapp1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration //Marks this class as a configuration class
@EnableWebSecurity //Enables Spring Security's web security support

//this is custom security config
public class SecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((requests)-> requests.anyRequest().authenticated()); 
		// ^|| All requests require authentication
		
//		http.formLogin(withDefaults());//browser provides form based we can login and  logout 
	// ^|| payload tab in form based authen... and no payload tab in basic authen...	
		
		http.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//cookie i.e session data is cleared as it is stateless 
		http.httpBasic(withDefaults());//popup login form and access by basic authentication here no login no logout facility
	// ^|| specifies security should use HTTP  basic authentication with default settings
		
		return http.build();//returning the security object of type SecurityFilterChain
	}
}
