package com.example.springsecuritywebmvc.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class ShoppingUserBean {

@Id
@Column(name="username")
private String userName;
@Column(name="password")
private String password;
@Column(name="enabled")
private int enabled;

@Column(name="firstname")
private String firstname;

@Column(name="lastname")
private String lastname;

@Column(name="email")
private String email;

public ShoppingUserBean() {
	
}

public ShoppingUserBean(String userName, String password, String firstname, String lastname,
		String email) {
	super();
	this.userName = userName;
	this.password = password;
	this.enabled = 1;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getEnabled() {
	return enabled;
}
public void setEnabled(int enabled) {
	this.enabled = enabled;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}



}
