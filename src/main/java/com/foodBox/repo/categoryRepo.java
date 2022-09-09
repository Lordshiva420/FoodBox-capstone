package com.foodBox.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foodBox.model.productCategory;

public interface categoryRepo extends JpaRepository<productCategory, Long> {

	public List<productCategory> findBycategoryName(String categoryName);


	
    
  
   
}
