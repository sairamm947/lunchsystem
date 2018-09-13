package com.cubic.lunch.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cubic.lunch.model.Accounts;
import com.cubic.lunch.model.Products;
import com.cubic.lunch.services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController implements ErrorController{
	
	private static final String PATH="/error";
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService service;
	
	@Autowired
	private EmployeeController EmployeeController;
	/*@Value("${block.url}")
	private boolean isBlock;*/
	
	@Autowired
	private Environment env;
	public String id;
	@GetMapping("/home")
	public ModelAndView home() {
		logger.info("Entered into home page controller method");
		ModelAndView mav=new ModelAndView("home");
		return mav;
	}
	
	@GetMapping("/registration")
	public ModelAndView login() {
		logger.info("Entered into before registraion controller method");
		ModelAndView mav=new ModelAndView("registration");
		mav.addObject("accounts", new Accounts());
		return mav;
	}
	
	@PostMapping("/registration")
	public ModelAndView empregistration(@Valid Accounts account, BindingResult result) {
		ModelAndView modelAndView ;
		if (result.hasErrors()) {
			logger.info("Error in registration form filling:: Controller");
			modelAndView = new ModelAndView("registration");
			modelAndView.addObject("user", account);
			return modelAndView;
		}
		service.register(account);
		logger.info("Registraion success:: controller");
		modelAndView = new ModelAndView("login");
		return modelAndView;
	
	}
	
	@RequestMapping("/login/{role}")
	public ModelAndView loginpage(@PathVariable("role")String role) {
		logger.info("Entered into login role page:"+role+":controller");
		ModelAndView mav=new ModelAndView("login");
		mav.addObject("role", role);
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("name")String name,
			@RequestParam("password")String password,
			@RequestParam("role")String role) {
		ModelAndView mav = new ModelAndView();
		logger.info("Entered into login role page:"+name+":"+password+":"+role+":controller");
		Accounts accounts=service.loginservice(name,password,role);
		EmployeeController.userinfo(accounts);
		mav.addObject("accounts", accounts);
		if (!(accounts==null)) {
			
		
		if ((accounts.getRole()).equals("EMPLOYEE")) {
			id=name;
			mav.setViewName("empprofile");
		} else {
			id=name;
			mav.setViewName("adminprofile");
		}
		}else {
			logger.info("User Not Valid or invalid credentials");
			mav.setViewName("login");
			mav.addObject("msg", "User Not Valid or invalid credentials");
		}
		return mav;
	}
	
	@GetMapping("/addproduct")
	public ModelAndView addproduct() {
		logger.info("Entered into before addproduct controller method");
		ModelAndView mav=new ModelAndView("addproduct");
		String value = env.getProperty("block.url");
		if(value.equalsIgnoreCase("true")) {
			mav.addObject("products", new Products());
		} else {
			mav.addObject("products", new Products());
		}
		
		
		return mav;
	}
	
	@PostMapping("/addproduct")
	public ModelAndView addproduct(@Valid Products products, BindingResult result) {
		ModelAndView modelAndView= new ModelAndView("addproduct");;
		logger.info(products.getProductid()+",:"+products.getProductName()+",:"+products.getPrice());
		if (result.hasErrors()) {
			logger.info("Error in addproduct form filling:: Controller");
			modelAndView.addObject("user", products);
			return modelAndView;
		}
		if (service.checkproduct(products.getProductid())) {
			service.addproduct(products);
			logger.info("Product added success:: controller");
			modelAndView.addObject("products", new Products());
			modelAndView.addObject("id", "Product Added to the Menu with Id : "+products.getProductid());
		} else {
			logger.info("Product already Exits:: controller");
			modelAndView.addObject("ids", "Product Already exits with Id : "+products.getProductid());
		}
		return modelAndView;
	
	}
	
	@GetMapping("/showproducts/{status}")
	public ModelAndView showproducts(@PathVariable("status")Integer status) {
		ModelAndView mav;
		if (status==01) {
			mav=new ModelAndView("showproducts");
		} else {
			mav=new ModelAndView("menu");
		}
		mav.addObject("", "");
		mav.addObject("lists", service.showproducts());
		return mav;
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

}
