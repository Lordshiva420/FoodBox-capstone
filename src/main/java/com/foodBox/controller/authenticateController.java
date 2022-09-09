package com.foodBox.controller;

import java.io.Console;
import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodBox.config.jwtUtil;
import com.foodBox.model.authority;
import com.foodBox.model.jwtRequest;
import com.foodBox.model.jwtResponse;
import com.foodBox.model.user;
import com.foodBox.repo.userRepo;
import com.foodBox.service.userDetaliServiceImpl;
import com.foodBox.service.userServiceImpl;

@RestController
@CrossOrigin("*")
public class authenticateController {
	
	
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	userDetaliServiceImpl userDetaliServiceImpl;
	
	@Autowired
	jwtUtil jwtUtil;
	
	
	
	//when user first time log in we will check if he is valide user or not , if he is then we will generate token
	
	//generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody jwtRequest jwtRequest) throws Exception {
		try {
			
			authenticate(jwtRequest.getUserName(),jwtRequest.getPassword());
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("user not found");
		}
		
		
        UserDetails UserDetails=this.userDetaliServiceImpl.loadUserByUsername(jwtRequest.getUserName());
       
		String token=this.jwtUtil.generateToken(UserDetails);
		
		return ResponseEntity.ok(new jwtResponse(token));
	}
	
	
	
	//passing user entered creditials and authenticating.
	
	private void authenticate(String username, String password) throws Exception {
		
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException e) {
			
			throw new Exception("user diabled");
	
		}catch(BadCredentialsException e)
		{
			throw new Exception("invalide creditials");
		}
		
	}
	
	//get the current loggedin user
	@GetMapping("/currentUser")
	public user getCurrentUser(Principal principal) {
	System.out.println("@@@@@@@@@@@@@@");
	
    
	System.out.println(principal.getName());
		return ((user)this.userDetaliServiceImpl.loadUserByUsername(principal.getName()));
		
		
	}

}
