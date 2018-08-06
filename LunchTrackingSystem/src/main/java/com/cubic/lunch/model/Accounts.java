package com.cubic.lunch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
public class Accounts implements Serializable {
	@Id
	@Column(name="Employee_Id",length=5,nullable=false)
	@NotNull
	private Integer employeeId;
	
	@Size(max=25,min=2)
	@NotNull
	private String name;
	
	@Email
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	private Integer mobile;
	
	@NotNull
	private String Role;
	
	@NotNull
	@Length(max=5,min=5)
	private String password;

	@Transient
	private Boolean active;

	
	public Integer getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getMobile() {
		return mobile;
	}


	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}


	public String getRole() {
		return Role;
	}


	public void setRole(String role) {
		Role = role;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public Accounts() {
		super();
	}


	@Override
	public String toString() {
		return "Accounts [employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", Role=" + Role + ", password=" + password + ", active=" + active + "]";
	}
	
	

}
