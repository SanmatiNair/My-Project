package com.niit.BoutiqueBack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productId;
	
	@Column(unique=true,nullable=false)
	@NotEmpty(message="name cannot be blank")
	private String productName;
	
	@NotEmpty(message="description cannot be blank")
	private String productDescription;
	
	@Min(value=1, message="quantity must be greater than 1")
	private int quantity;
	
	@Min(value=100, message="price must be greater than 100")
	private float price;
	
	private int catid;
	
	@Transient
	MultipartFile pimage;
	
	
	public MultipartFile getPimage() {
		return pimage;
	}
	public void setPimage(MultipartFile pimage) {
		this.pimage = pimage;
	}
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
	
	
	
	
	