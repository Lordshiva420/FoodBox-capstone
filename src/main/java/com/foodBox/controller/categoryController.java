package com.foodBox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodBox.model.product;
import com.foodBox.model.productCategory;
import com.foodBox.repo.categoryRepo;
import com.foodBox.service.categoryService;
import com.foodBox.service.productService;

import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class categoryController {
	
	@Autowired
	categoryService catService;
	@Autowired
	categoryRepo categoryRepo;
	
	
	@PostMapping("/addcategory")
	public productCategory createCategory(@RequestBody productCategory category) throws Exception {
		
		
		if(category.getcId()==0) {
			
			return this.catService.addCategory(category);
		
		}else {
			return this.catService.updateCategory(category);
		
		}
	}
	
	@GetMapping("/getallcategories")
	public List<productCategory> listofcaegories() {
		
		return this.catService.getallcategories();
		
	}
	//get category by id
		@GetMapping("/getCategoryByid/{id}")
		public productCategory getCategoriesByid(@PathVariable("id") Long id) {
			
			return this.catService.getCategoryById(id);
			
			
		}
	
	@PutMapping("/updatecategory")
	public productCategory updateProduct(@RequestBody productCategory category) throws Exception {
		
		
		return this.catService.updateCategory(category);
		
	}
	
	@DeleteMapping("/deleteCategory/{cId}")
	public void deleteProduct(@PathVariable("cId") long cid) {
		
		this.catService.deleteCategoryById(cid);
	}

}
