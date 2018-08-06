package com.cubic.lunch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cubic.lunch.model.Products;

public interface ProductDao extends JpaRepository<Products, Integer>{

	@Query(nativeQuery=true, value="Select p.product_id from products p where p.product_id=:productid")
	String findByProductId(@Param("productid")String productid);

}
