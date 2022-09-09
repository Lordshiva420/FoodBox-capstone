package com.foodBox.controller;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foodBox.model.product;
import com.foodBox.model.productCategory;
import com.foodBox.repo.categoryRepo;
import com.foodBox.service.categoryService;
import com.foodBox.service.productService;
@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class productController {
	@Autowired
	categoryService catService;
	@Autowired
	categoryRepo categoryRepo;
	@Autowired
	productService proService;

//	storing incomin file and clling addproduct method
	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })

	public ResponseEntity<Object> uploadFiles(@RequestPart(name = "product") product pro,
			@RequestPart(name = "image") MultipartFile[] files) {

		try {
			List<String> fileNames = new ArrayList<>();

			// read and write the file to the local folder
			Arrays.asList(files).stream().forEach(file -> {
				byte[] bytes = new byte[0];
				try {
					bytes = file.getBytes();
					Path path = Paths.get(FileUtil.folderPath + file.getOriginalFilename());
					Files.write(path, bytes);
					String subpath = file.getOriginalFilename().toString();
					fileNames.add(subpath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			pro.setImageUrl(fileNames.get(0));
			createProduct(pro);
			return ResponseEntity.status(HttpStatus.OK).body("Files uploaded successfully: " + fileNames);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Exception to upload files!");
		}
	}

//  add the product

	public product createProduct(product product) throws Exception {
		if (product.getCategory().getcId() != 0) {
			productCategory category = this.catService.getCategoryById(product.getCategory().getcId());
			System.out.println(category + "category");
			if (category != null) {
				System.out.println(this.proService.addProduct(product, category));
				return this.proService.addProduct(product, category);

			} else {
				throw new Exception("unable to add product");
			}
		} else {

			productCategory newcategory = new productCategory();
			newcategory.setCategoryName(product.getCategory().getCategoryName());
			productCategory cate = this.categoryRepo.save(newcategory);
			return this.proService.addProduct(product, cate);

		}
	}
// get all products
	@GetMapping("/getallproducts")
	public List<product> listofproducts() {

		return this.proService.getallproducts();

	}

	// get product by productname
	@GetMapping("/{productname}")
	public product getUser(@PathVariable("productname") String productName) {

		return this.proService.getproductByName(productName);

	}

	// get product by productid
	@GetMapping("productId/{pid}")
	public product getproduct(@PathVariable("pid") long pid) {

		return this.proService.getproductByid(pid);

	}

	@PutMapping("/updateProduct")
	public product updateProduct(@RequestBody product product) throws Exception {

		return this.proService.editProduct(product);

	}

	@DeleteMapping("/deleteproduct/{pId}")
	public void deleteProduct(@PathVariable("pId") long pid) {

		this.proService.deleteProductById(pid);
	}

	@GetMapping("/searchproduct/{keyword}")
	public List<product> deleteProduct(@PathVariable("keyword") String keyword) {

		return this.proService.searchProduct(keyword);
	}
}
