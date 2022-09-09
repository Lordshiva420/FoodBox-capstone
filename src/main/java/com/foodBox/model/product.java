package com.foodBox.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="products")

public class product {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long pId;
	private String productName;
	private String productDescription;
	private String imageUrl;
	private float unitPrice;
	private int quantity;
	private boolean active=true;
	@ManyToOne()
	@JsonBackReference
	private productCategory category;
	
	
	public product() {
		super();
		// TODO Auto-generated constructor stub
	}


	public product( String productName, String productDescription, String imageUrl, float unitPrice,
			int quantity, boolean active, productCategory category) {
		super();
		
		this.productName = productName;
		this.productDescription = productDescription;
		this.imageUrl = imageUrl;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.active = active;
		this.category = category;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public float getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public productCategory getCategory() {
		return category;
	}


	public void setCategory(productCategory category) {
		this.category = category;
	}


	public long getpId() {
		return pId;
	}


	public void setpId(long pId) {
		this.pId = pId;
	}


	@Override
	public String toString() {
		return "product [pId=" + pId + ", productName=" + productName + ", productDescription=" + productDescription
				+ ", imageUrl=" + imageUrl + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", active="
				+ active + ", category=" + category + "]";
	}
	
	
	
	
	
}
