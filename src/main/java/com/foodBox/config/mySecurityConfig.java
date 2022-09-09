package com.foodBox.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.foodBox.service.userDetaliServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class mySecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private userDetaliServiceImpl userDetaliServiceImpl;
	@Autowired
	private jwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private jwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	//to encrypt the incoming password.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	// authenticate the user when generating token.
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}


    // passing DB userdetails for authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	auth.userDetailsService(this.userDetaliServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		
		     .csrf()
		     .disable()
		     .cors()
		     .disable()
		     .authorizeRequests()
		     // this requests wont get authenticated permited for all.
		     .antMatchers("/generate-token","/user/","/product/**","/category/**").permitAll()
		     .antMatchers(HttpMethod.OPTIONS).permitAll()
		     .anyRequest().authenticated()
		     .and()
		     .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		     .and()
		     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		      
		
	}

	

}
