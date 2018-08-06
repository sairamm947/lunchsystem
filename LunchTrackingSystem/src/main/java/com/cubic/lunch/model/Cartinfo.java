package com.cubic.lunch.model;

public class Cartinfo {

	private Integer TotalAount;
	private Products products;
	
	
	public Cartinfo(Integer totalAount, Products products) {
		super();
		TotalAount = totalAount;
		this.products = products;
	}
	public Integer getTotalAount() {
		return TotalAount;
	}
	public void setTotalAount(Integer totalAount) {
		TotalAount = totalAount;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Cartinfo [TotalAount=" + TotalAount + ", products=" + products + "]";
	}
	
	
}
