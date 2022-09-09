package com.foodBox.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodBox.model.UserRole;
import com.foodBox.model.role;


public interface roleRepo  extends JpaRepository<role,Long> {

	

}
