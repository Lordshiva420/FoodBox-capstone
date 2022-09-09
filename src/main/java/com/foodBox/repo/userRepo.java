package com.foodBox.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodBox.model.user;

public interface userRepo extends JpaRepository<user,Long> {

	user findByUserName(String userName);
	

}
