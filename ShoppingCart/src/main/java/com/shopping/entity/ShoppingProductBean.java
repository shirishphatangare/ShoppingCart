package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class ShoppingProductBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="productId")
	private int id;

	@Column(name="productName")
	private String productName;
	
	@Column(name="productAuthor")
	private String productAuthor;

	@Column(name="productPrice")
	private int productPrice;
	
	public ShoppingProductBean() {
		
	}
	
	

	public ShoppingProductBean(String productName, String productAuthor, int productPrice) {
		super();
		this.productName = productName;
		this.productAuthor = productAuthor;
		this.productPrice = productPrice;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
		
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAuthor() {
		return productAuthor;
	}

	public void setProductAuthor(String productAuthor) {
		this.productAuthor = productAuthor;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

}
