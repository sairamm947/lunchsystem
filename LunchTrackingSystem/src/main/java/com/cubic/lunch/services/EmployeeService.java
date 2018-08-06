package com.cubic.lunch.services;

import java.util.HashMap;

import com.cubic.lunch.model.Accounts;
import com.cubic.lunch.model.Cartinfo;
import com.cubic.lunch.model.Products;

public interface EmployeeService {

	HashMap<String, Cartinfo> cart();

	Integer addcart(Products products, Integer quantity);

	HashMap<String, Cartinfo> order();

	Accounts user(Integer eid);

	void deletecart();

	Integer finalAmount();

	void conformorder();

	void removesinglecart(String itemid);

}
