package com.cubic.lunch.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/links")
public class LinksController {
	
	
	private static final Logger  log = LoggerFactory.getLogger(LinksController.class);

	@RequestMapping("/emp")
	public ModelAndView empprofile() {
		log.info("going to Employee profile");
		
		return new ModelAndView("empprofile");
	}
	
	@RequestMapping("/adm")
	public ModelAndView admprofile() {
		log.info("going to Admin profile");
		return new ModelAndView("adminprofile");
	}

}
