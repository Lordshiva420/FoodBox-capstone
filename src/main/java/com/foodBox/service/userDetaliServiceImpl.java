package com.foodBox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodBox.model.user;
import com.foodBox.repo.userRepo;

@Service
public class userDetaliServiceImpl implements UserDetailsService {
	
	@Autowired
	 private userRepo userRepo;
	
// get the user from DB by username.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user user=this.userRepo.findByUserName(username);
		
		if(user==null) {
			System.out.println("no user found with given username");
			throw new UsernameNotFoundException("user not found..!");
		}
		return user;
	}

}
