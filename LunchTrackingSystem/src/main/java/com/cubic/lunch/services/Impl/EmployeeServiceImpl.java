package com.cubic.lunch.services.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubic.lunch.dao.AdminDao;
import com.cubic.lunch.dao.OrderDetailsDao;
import com.cubic.lunch.model.Accounts;
import com.cubic.lunch.model.Cartinfo;
import com.cubic.lunch.model.OrderDetails;
import com.cubic.lunch.model.Products;
import com.cubic.lunch.services.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private AdminDao adminDao; 
	
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	HashMap<String, Cartinfo> hashmap=new HashMap<String, Cartinfo>();
	@Override
	public HashMap<String,Cartinfo> cart() {
		log.info("Entered into getting cart :: Employee service");
		return hashmap;
	}

	@Override
	public Integer addcart(Products products, Integer quantity) {
		log.info("Entered into cart adding ::Employee service");
		int totalprice=quantity*products.getPrice();
		hashmap.put(products.getProductid(), new Cartinfo(totalprice, products));
		log.info("Entered into cart added ::Employee service");
		return totalprice;
		
	}

	@Override
	public HashMap<String,Cartinfo> order() {
		
		return hashmap;
	}

	@Override
	public Accounts user(Integer eid) {
		Optional<Accounts> userdetails=adminDao.findById(eid);
		return null;
	}

	@Override
	public void deletecart() {
		//hashmap.clone();
		hashmap.clear();
		
	}
	
	public Integer finalAmount() {
		int amount=0;
		for (Map.Entry<String,Cartinfo> m : hashmap.entrySet()) {
			amount +=m.getValue().getTotalAount();
		}
		log.info("Total amount :"+amount);
		return amount;
		
	}

	

	@Override
	public void removesinglecart(String itemid) {
		hashmap.remove(itemid);
	}

	@Override
	public String conformorder(Accounts userdata) {
		log.info("Entered into conform order serviceImpl method with userid :: " + userdata);
		if (!(hashmap.isEmpty())) {
			String x = null;
			try {
				log.info("selecting last orderid");
				x = orderDetailsDao.lastOrderId();
			} catch (Exception e) {
				log.info("last OrderId not available");
				System.out.println(e);
			}
			log.info("last orderid" + x);
			if (x.equals(null)) {
				x = "ORDE_0000";
			}
			StringTokenizer str = new StringTokenizer(x, "_");
			String fx = str.nextToken().concat("_");
			String lx = str.nextToken();
			int y = Integer.parseInt(lx) + 1;
			String orderid = fx + y;
			log.info("Entered into conform order serviceImpl method with Order ID::" + orderid);

			for (Map.Entry<String, Cartinfo> has : hashmap.entrySet()) {
				// Accounts a=adminDao.findOne(id);
				OrderDetails order = new OrderDetails();
				order.setAccounts(userdata);
				log.info("" + userdata);
				String prodid = has.getKey();
				order.setProdid(prodid);
				order.setDate(new Date());
				order.setPrice(has.getValue().getTotalAount());
				order.setOrderid(orderid);
				try {
					orderDetailsDao.save(order);
					log.info("Entered into conform order serviceImpl method with Order saved with::" + order);
				} catch (Exception e) {
					return "Order Not saved Product is" + prodid;
				}
			}
			log.info("Order saved With :  " + orderid);
			return orderid;
		} else {

			log.info("Order Not saved :  ");
			return "no";
		}

	}

}
