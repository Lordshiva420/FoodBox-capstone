package com.foodBox.service;

import java.util.List;

import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodBox.model.product;
import com.foodBox.model.productCategory;

import com.foodBox.repo.categoryRepo;
import com.foodBox.repo.productRepo;
@Service
public class productService {
	
	@Autowired
	productRepo productRepo;
	@Autowired
	categoryRepo categoryRepo;
	
//add product to table
	
public product addProduct(product product, productCategory category)  {
	
	product.setCategory(category);
	return this.productRepo.save(product);
        
}

//get all products

public  List<product> getallproducts() {
	
	return this.productRepo.findAll();
	
	
}

//get product by product name

public product getproductByName(String proName) {
	
		return this.productRepo. findByProductName(proName);
		
}
//get product by product id

public product getproductByid(Long id) {
	
		return this.productRepo.findById(id).get();
}

//update the product
public product editProduct(product product) {
	
	return this.productRepo.save(product);
}
	


//delete product

public void deleteProductById(long id) {

	
	this.productRepo.deleteByid(id);
	System.out.println();
	
	
	
}

// Search product by name or keyword

public List<product> searchProduct(String keyword){
	
	
	return this.productRepo.findByProductNameContaining(keyword);
	
	
}



 
}
