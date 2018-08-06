package com.cubic.lunch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Products implements Serializable{

	@Id
	@NotNull
	@Column(name="Product_Id",length=7,nullable=false)
	private String productid;
	
	@NotNull
	private String productName;
	
	@NotNull
	private Integer price;

	
	public String getProductid() {
		return productid;
	}


	public void setProductid(String productid) {
		this.productid = productid;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public Products() {
		super();
	}


	@Override
	public String toString() {
		return "Products [productid=" + productid + ", productName=" + productName + ", price=" + price + "]";
	}
	
	
	

}
