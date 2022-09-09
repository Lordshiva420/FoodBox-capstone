package com.foodBox.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodBox.model.UserRole;
import com.foodBox.model.role;
import com.foodBox.model.user;
import com.foodBox.repo.roleRepo;
import com.foodBox.repo.userRepo;
@Service
public class userServiceImpl implements userService{
	
	@Autowired
	private userRepo userRepo;
	
	@Autowired
	private roleRepo roleRepo;
	
	
     //creating user
	@Override
	public user createuser(user user, Set<UserRole> userRoles) throws Exception {
		
		user localUser=this.userRepo.findByUserName(user.getUsername());
		
		//checking if user already exist in DB
		
		if(localUser!=null) {
			System.out.println("user already present");
			throw new Exception("user already present");
			
		}else {
		//creating new user
			
				//saving roles
				for(UserRole ur:userRoles) {
					
					roleRepo.save(ur.getRole());
				  }
			
				//setting roles to user
				user.setUserRoles(userRoles);
				
				//saving user
				localUser=this.userRepo.save(user);
				
			}
		
		return localUser;
	}

//get user from username
	
	@Override
	public user findByUserName(String userName) {
		
		
		return this.userRepo.findByUserName(userName);
	}
	
//delete user by user id
	@Override
	public void deleteUser(long uId) {
		
		this.userRepo.deleteById(uId);
	}
	
}
