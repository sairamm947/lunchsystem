package com.cubic.lunch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cubic.lunch.model.Accounts;
import com.cubic.lunch.model.Products;

@Repository
public interface AdminDao extends JpaRepository<Accounts, Integer>{

	@Query(nativeQuery=true, value="select a.* from accounts a where a.Employee_Id=:name And a.password=:password")
	Accounts getAccount(@Param("name")String name,@Param("password")String password);

	//void save(Products products);
}
