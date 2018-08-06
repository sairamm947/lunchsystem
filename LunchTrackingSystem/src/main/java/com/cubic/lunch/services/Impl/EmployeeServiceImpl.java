package com.cubic.lunch.services.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubic.lunch.dao.AdminDao;
import com.cubic.lunch.model.Accounts;
import com.cubic.lunch.model.Cartinfo;
import com.cubic.lunch.model.Products;
import com.cubic.lunch.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private AdminDao adminDao; 
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	HashMap<String, Cartinfo> hashmap=new HashMap<String, Cartinfo>();
	@Override
	public HashMap<String,Cartinfo> cart() {
		log.info("Entered into getting cart :: Employee service");
		return hashmap;
	}

	@Override
	public Integer addcart(Products products, Integer quantity) {
		log.info("Entered into cart adding ::Employee service");
		int totalprice=quantity*products.getPrice();
		hashmap.put(products.getProductid(), new Cartinfo(totalprice, products));
		log.info("Entered into cart added ::Employee service");
		return totalprice;
		
	}

	@Override
	public HashMap<String,Cartinfo> order() {
		
		return hashmap;
	}

	@Override
	public Accounts user(Integer eid) {
		Optional<Accounts> userdetails=adminDao.findById(eid);
		return null;
	}

	@Override
	public void deletecart() {
		//hashmap.clone();
		hashmap.clear();
		
	}
	
	public Integer finalAmount() {
		int amount=0;
		for (Map.Entry<String,Cartinfo> m : hashmap.entrySet()) {
			amount +=m.getValue().getTotalAount();
		}
		log.info("Total amount :"+amount);
		return amount;
		
	}

	@Override
	public void conformorder() {
		
		
	}

	@Override
	public void removesinglecart(String itemid) {
		hashmap.remove(itemid);
	}

}
