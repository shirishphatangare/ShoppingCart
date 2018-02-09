package com.shopping.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@OneToMany(mappedBy="order")
	private List<ShoppingProductBean> products;
	
	@OneToOne
	@JoinColumn(name="fk_user")
	private ShoppingUserBean user;

	
	@Column(name="productquantity")
	private int productQuantity;

	@Column(name="totalproductprice")
	private int totalProductPrice;

	@Column(name="orderprice")
	private int orderPrice;

}
