package com.cubic.lunch.services.Impl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubic.lunch.dao.AdminDao;
import com.cubic.lunch.dao.ProductDao;
import com.cubic.lunch.model.Accounts;
import com.cubic.lunch.model.Products;
import com.cubic.lunch.services.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminDao admindao;
	
	@Autowired
	private ProductDao productdao;
	
	@Override
	@Transactional(rollbackOn=Exception.class)
	public void register(Accounts account) {
		try {
			admindao.save(account);
		} catch (Exception e) {
			logger.info("empoyee not registered :: repository");
		}
		
	}

	@Override
	public Accounts loginservice(String name, String password,String role) {
		Accounts account=null;
		try {
			account =admindao.getAccount(name,password);
		} catch (Exception e) {
			logger.info("User not Available");
		}
		return account;
	}

	@Override
	public void addproduct(Products products) {
		try {
			productdao.save(products);
		} catch (Exception e) {
			logger.info("product not Added :: repository");
		}
		
	}

	@Override
	public List<Products> showproducts() {
		List<Products> list = null;
		try {
			list=productdao.findAll();
		} catch (Exception e) {
			logger.info("Products not getting:: repository");
		}
		return list;
	}

	@Override
	public boolean checkproduct(String productid) {
		try {
			logger.info(productdao.findByProductId(productid));
			if (productdao.findByProductId(productid)==null) {
				logger.info("Product Not Exits :: repository");
				return true;
			} 
		} catch (Exception e) {
			logger.info("Exception in product Checking is exits or not");
		}
		return false;
	}

	}
