package com.example.springsecuritywebmvc.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="productDetails")
public class ShoppingProductDetailBean {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@OneToOne
	@JoinColumn(name="productId")
	private ShoppingProductBean product;

	@Column(name="productTotalPrice")
	private int productTotalPrice;
	
	@Column(name="productQuantity")
	private int productQuantity;
	
	public ShoppingProductDetailBean() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public ShoppingProductBean getProduct() {
		return product;
	}


	public void setProduct(ShoppingProductBean product) {
		this.product = product;
	}


	public int getProductTotalPrice() {
		return productTotalPrice;
	}

	public void setProductTotalPrice(int productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
		
}
