package com.springmavenfirst.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springmavenfirst.dao.IEmployeeDao;
import com.springmavenfirst.model.Employee;

@Repository
public class IEmployeeDaoImpl implements IEmployeeDao{

	@Autowired
	public SessionFactory sessionFactory;
	
	@Transactional
	public int saveEmployeeImpl(Employee e) {
		int eid = 0;
		try {
			Session session=sessionFactory.getCurrentSession();
			eid=((Integer) session.save(e));
		} catch (Exception e2) {
			System.out.println("employee not saved");
		}
		return eid;
		
	}

}
