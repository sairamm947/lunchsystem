package com.cubic.lunch.controllers;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cubic.lunch.model.Accounts;
import com.cubic.lunch.model.Cartinfo;
import com.cubic.lunch.model.Products;
import com.cubic.lunch.services.AdminService;
import com.cubic.lunch.services.EmployeeService;

@RestController
@RequestMapping("/employee")
@SessionAttributes("xyz")
public class EmployeeController implements ErrorController{
	
	private static final String PATH="/error";
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@ModelAttribute("xyz")
	public String getXyz() {
		return "shankar";
	}
	@RequestMapping(value=PATH)
	public String DefaultErrorMessage() {
		return "Requested Resource Not Available !!!";
	}
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private EmployeeService cartservice;
	
	@GetMapping("/showproduct")
	public ModelAndView showproducts(@ModelAttribute("xyz") String xyz) {
		System.out.println(xyz);
		ModelAndView mav=new ModelAndView("showproducts");
		mav.addObject("lists", service.showproducts());
		mav.addObject("product", new Products());
		return mav;
	}
	
	@GetMapping("/showproducts/02/{no}")
	public ModelAndView showproduct(@PathVariable("no")String no) {
		ModelAndView mav;
		mav=new ModelAndView("menu");
		mav.addObject("lists", service.showproducts());
		mav.addObject("cart", cartservice.cart());
		mav.addObject("price", cartservice.finalAmount());
		if (no.equals("12")) {
			mav.addObject("status", "Your Cart is Empty Please Select items");
		}
		return mav;
	}
	
	@PostMapping("/addcart")
	public ModelAndView addproduct(Products products,@RequestParam("quantity")Integer quantity) {
		logger.info("Entered into addproduct cart:: controller"+products.getProductid()+","+products.getProductName()+","+products.getPrice()+","+quantity);
		cartservice.addcart(products,quantity);	
		//mav.addObject("price", cartservice.finalAmount());
		return new ModelAndView("redirect:/employee/showproducts/02/02");
	}
	@RequestMapping("/order")
	public ModelAndView order() {
		ModelAndView mav= new ModelAndView();
		HashMap<String,Cartinfo> hashmap=cartservice.order();
		System.out.println(hashmap);
		if (hashmap.isEmpty()) {
			
			mav.setViewName("redirect:/employee/showproducts/02/12");
		}else {
			mav.addObject("items", hashmap);
			mav.addObject("price", cartservice.finalAmount());
			
			mav.setViewName("finalorder");
		}
		return mav;
	}
	
	@GetMapping("/cartremove")
	public ModelAndView cartremove() {
		cartservice.deletecart();
		return new ModelAndView("redirect:/employee/showproducts/02/02");
		
	}
	@PostMapping("/remove")
	public ModelAndView remove(@RequestParam("itemid")String itemid) {
		cartservice.removesinglecart(itemid);
		logger.info("Entered into single item remove from cart done:: controller");
		return new ModelAndView("redirect:/employee/showproducts/02/02");
	}
	@RequestMapping("/orderconform")
	public ModelAndView orderconform() {
		cartservice.conformorder();
		return null;
		
	}
		
}