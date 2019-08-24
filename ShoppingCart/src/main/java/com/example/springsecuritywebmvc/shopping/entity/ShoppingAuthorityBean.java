package com.example.springsecuritywebmvc.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class ShoppingAuthorityBean {
	
	@Id
	@Column(name="username")
	private String userName;
	@Column(name="authority")
	private String authority;
	
	public ShoppingAuthorityBean(String userName, String authority) {
		super();
		this.userName = userName;
		this.authority = authority;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
}
