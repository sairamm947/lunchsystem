package com.cubic.lunch.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer sno;
	
	private String orderid;

	private Integer price;
	
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private Accounts accounts;
	
	private String prodid;
	
	
	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	public OrderDetails() {
		super();
	}


	public Integer getSno() {
		return sno;
	}


	public void setSno(Integer sno) {
		this.sno = sno;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Accounts getAccounts() {
		return accounts;
	}


	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}


	public String getProdid() {
		return prodid;
	}


	public void setProdid(String prodid) {
		this.prodid = prodid;
	}


	@Override
	public String toString() {
		return "OrderDetails [sno=" + sno + ", orderid=" + orderid + ", price=" + price + ", date=" + date
				+ ", accounts=" + accounts + ", prodid=" + prodid + "]";
	}
	
	
	


}
