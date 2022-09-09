package com.foodBox.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="categories")
public class productCategory {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long cId;
	private String categoryName;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="category")
	@JsonManagedReference
	private Set<product> products;

	public productCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public productCategory(String categoryName, Set<product> products) {
		super();
		this.categoryName = categoryName;
		this.products = products;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<product> getProducts() {
		return products;
	}

	public void setProducts(Set<product> products) {
		this.products = products;
	}

	public long getcId() {
		return cId;
	}

	public void setcId(long cId) {
		this.cId = cId;
	}

	
	

}
