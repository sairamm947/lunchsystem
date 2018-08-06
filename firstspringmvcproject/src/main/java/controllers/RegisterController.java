package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bean.Student;
import registerService.IregisterService;

@Controller
public class RegisterController {
	
	
	@Autowired
	public IregisterService service;
	
	@RequestMapping("/register")
	public String showRegisterPage()
	{
		return "register";
	}

	@RequestMapping(value="/registerstudent", method=RequestMethod.POST)  //@webservlet("/")
	public ModelAndView insertStudent(@ModelAttribute("student") Student student, HttpServletRequest request) //doPost()
	{
		System.out.println("into registerstudent controller");
		
		System.out.println(student.getStudentname());
		System.out.println(student.getMnumber());
		System.out.println(student.getPassword());
		
		int id=service.register(student);
		System.out.println(id);
		ModelAndView mav=new ModelAndView("success");
		mav.addObject("s", id);
		
		return mav;
		
		
	}
	
	
	
}
