package com.cubic.lunch.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cubic.lunch.model.OrderDetails;

@Repository
@Transactional
@Component
public interface OrderDetailsDao extends JpaRepository<OrderDetails, Integer>{

	//void save(OrderDetails order);

	@Query(nativeQuery=true, value="select od.orderid from order_details od where od.sno=(select max(sno) from order_details)")
	public String lastOrderId();

}
