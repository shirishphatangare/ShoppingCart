package com.example.springbootsecurity.shopping.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderdetails")
public class ShoppingOrderDetailBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderId")
	private int id;

	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="ORDER_PRODUCT", joinColumns=@JoinColumn(name="order_id"),
	inverseJoinColumns=@JoinColumn(name="product_id"))
	private List<ShoppingProductDetailBean> products = new ArrayList<ShoppingProductDetailBean>();
	
	@OneToOne
	@JoinColumn(name="username")
	private ShoppingUserBean user;

	@Column(name="orderDate")
	private Date orderDate;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<ShoppingProductDetailBean> getProducts() {
		return products;
	}

	public void setProducts(List<ShoppingProductDetailBean> products) {
		this.products = products;
	}

	public ShoppingUserBean getUser() {
		return user;
	}

	public void setUser(ShoppingUserBean user) {
		this.user = user;
	}

	
	/*@Column(name="productquantity")
	private int productQuantity;

	@Column(name="totalproductprice")
	private int totalProductPrice;*/

	@Column(name="orderprice")
	private int orderPrice;

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	
	
	
}
