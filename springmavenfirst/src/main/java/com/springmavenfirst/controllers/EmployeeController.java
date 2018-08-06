package com.springmavenfirst.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmavenfirst.model.Employee;
import com.springmavenfirst.service.IEmployeeService;


@Controller
public class EmployeeController {
	
	@Autowired
	public IEmployeeService service;
	
	@RequestMapping("/register")
	public String showregister() 
	{
		return "register";
		
	}
	
	@RequestMapping("/login")
	public String showlogin() {
		
		return "login";
	}
	/*@RequestMapping(value="/registerservlet",method=RequestMethod.POST)
	public ModelAndView registerservlet(@Valid @ModelAttribute("employee")Employee employee,BindingResult result) {
		
		ModelAndView mav=null;
		if(result.hasErrors())
		{
			mav=new ModelAndView("register");
		}
		else
		{
		int eid=service.saveEmployee(employee);
		String msg="Employee saved with ID: "+eid;
		
		mav=new ModelAndView("success");
				
		}
		
		return mav;
	}
	*/
	@RequestMapping(value="/registerservlet",method=RequestMethod.POST)
	public ModelAndView registerservlet(@ModelAttribute("employee")Employee employee) {
		
		int id=service.saveEmployee(employee);
		//String msg="Employee saved with ID: "+eid;
		//System.out.println(msg);
		System.out.println(id);
		ModelAndView mav=new ModelAndView("success");
		mav.addObject(employee);
		return mav;
	}
	@RequestMapping(value="/logincontroller")
	public ModelAndView loginservlet(@RequestParam String name,@RequestParam String password) {
		System.out.println(name+password);
		String lg=name+password;
		ModelAndView mode=new ModelAndView("loged");
		mode.addObject("log", lg);  //to send values
		mode.addObject("head", "you are in login page"); // to send text
		return mode;
		
		
	}
}
