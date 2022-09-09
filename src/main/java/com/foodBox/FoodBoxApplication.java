package com.foodBox;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.foodBox.model.UserRole;
import com.foodBox.model.product;
import com.foodBox.model.productCategory;
import com.foodBox.model.role;
import com.foodBox.model.user;
import com.foodBox.service.categoryService;
import com.foodBox.service.productService;
import com.foodBox.service.userServiceImpl;

import net.bytebuddy.asm.Advice.This;


@SpringBootApplication
public class FoodBoxApplication implements CommandLineRunner{
@Autowired
userServiceImpl userServiceImpl;

@Autowired
categoryService catService;
@Autowired
productService proService;

	public static void main(String[] args) {
		SpringApplication.run(FoodBoxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("foodbox is ruuning");

	}

}
