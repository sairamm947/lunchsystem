package com.springmavenfirst.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmavenfirst.dao.IEmployeeDao;
import com.springmavenfirst.model.Employee;
import com.springmavenfirst.service.IEmployeeService;


@Service
public class EmployeeServiceImpl implements IEmployeeService{
	@Autowired
	public IEmployeeDao dao;
	public int saveEmployee(Employee e) {
		
		String password=e.getPassword();
		if (password.equals(e.getRepassword())) {
			e.setPassword(password);
			
		}else {
			//i want to display error in register page 
		}
		
		return dao.saveEmployeeImpl(e);
	}

}
