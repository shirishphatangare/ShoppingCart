package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order")
    private ShoppingOrderDetailBean order;
	
	public ShoppingProductBean() {
		
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
