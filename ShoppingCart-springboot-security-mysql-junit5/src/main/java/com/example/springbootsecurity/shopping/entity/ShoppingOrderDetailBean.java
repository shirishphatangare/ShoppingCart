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
	@Column(name="orderid")
	private Long id;

	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="ORDER_PRODUCT", joinColumns=@JoinColumn(name="order_id"),
	inverseJoinColumns=@JoinColumn(name="product_id"))
	private List<ShoppingProductDetailBean> products = new ArrayList<ShoppingProductDetailBean>();
	
	@OneToOne
	@JoinColumn(name="username")
	private ShoppingUserBean user;

	@Column(name="orderdate")
	private Date orderDate;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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


	@Override
	public String toString() {
		return "ShoppingOrderDetailBean [id=" + id + ", products=" + products + ", user=" + user + ", orderDate="
				+ orderDate + ", orderPrice=" + orderPrice + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderPrice;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingOrderDetailBean other = (ShoppingOrderDetailBean) obj;
		/*if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;*/
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (orderDate.getDate() != other.orderDate.getDate()) // Date equality adjusted to Date number for testing
			return false;
		if (orderPrice != other.orderPrice)
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
