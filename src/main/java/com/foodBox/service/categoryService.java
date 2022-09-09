package com.foodBox.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodBox.model.UserRole;
import com.foodBox.model.product;
import com.foodBox.model.productCategory;
import com.foodBox.model.user;
import com.foodBox.repo.categoryRepo;
import com.foodBox.repo.productRepo;

import net.bytebuddy.asm.Advice.This;

@Service
public class categoryService {
	@Autowired
	categoryRepo categoryRepo;
	@Autowired
	productRepo productRepo;
	
//add category to table
	
public productCategory addCategory(productCategory category) throws Exception {
	
	return this.categoryRepo.save(category);
        
}
	
// get all categories

public List<productCategory> getallcategories() {
	
	return this.categoryRepo.findAll();
	
	
}
//get category by category id


public productCategory getCategoryById(long id) {
	
		return this.categoryRepo.findById(id).get();
		
}

// get category by category name

public List<productCategory> getCategoryByName(String catName) {
	
		return this.categoryRepo. findBycategoryName(catName);
		
}

//delete category

public void deleteCategoryById(long id) {
	
	this.categoryRepo.deleteById(id);
	
}
//update category
public productCategory updateCategory(productCategory category) {
	
	return this.categoryRepo.save(category);
	
}






}

	