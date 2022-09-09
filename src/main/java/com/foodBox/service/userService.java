package com.foodBox.service;

import java.util.Set;

import com.foodBox.model.UserRole;
import com.foodBox.model.user;

public interface userService {
	
//	creating a user
	public user createuser(user user, Set<UserRole> userRoles) throws Exception;
// get user by username
	public user findByUserName(String userName);
	
// delete user by userId
	public void deleteUser(long uId);
}
