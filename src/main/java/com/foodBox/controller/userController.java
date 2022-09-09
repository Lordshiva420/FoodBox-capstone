package com.foodBox.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodBox.model.UserRole;
import com.foodBox.model.role;
import com.foodBox.model.user;
import com.foodBox.service.userServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class userController {
	
	@Autowired
	userServiceImpl userServiceImpl;
	
	//registration or creating user
	@PostMapping("/")
	public user createUser(@RequestBody user user) throws Exception {
		
	  Set<UserRole> userRoleSet=new HashSet<>();
		role role1=new role();
		role1.setrId(100);
		role1.setRollName("normal");
		
		UserRole userRole=new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);      
        userRoleSet.add(userRole);
		
		return this.userServiceImpl.createuser(user, userRoleSet);
		
		}
	
	//get user by username
	@GetMapping("/{username}")
	public user getUser(@PathVariable("username")String userName) {
		
		return this.userServiceImpl.findByUserName(userName);
		
		
	}
	
	//delete user by user id
	
     @DeleteMapping("/{uId}")
     public void deleteUser(@PathVariable("uId") long uId) {
    	 this.userServiceImpl.deleteUser(uId);
     }
}
