package com.cubic.lunch.services;

import java.util.List;

import javax.validation.Valid;

import com.cubic.lunch.model.Accounts;
import com.cubic.lunch.model.Products;

public interface AdminService {

	void register(Accounts account);

	Accounts loginservice(String name, String password,String role);

	void addproduct(@Valid Products products);

	List<Products> showproducts();

	boolean checkproduct(String productid);

	String pdfgen();
}
