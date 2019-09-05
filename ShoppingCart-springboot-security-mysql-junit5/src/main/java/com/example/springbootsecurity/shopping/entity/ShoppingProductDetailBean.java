package com.example.springbootsecurity.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="productdetails")
public class ShoppingProductDetailBean {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@OneToOne
	@JoinColumn(name="productid")
	private ShoppingProductBean product;

	@Column(name="producttotalprice")
	private int productTotalPrice;
	
	@Column(name="productquantity")
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


	@Override
	public String toString() {
		return "ShoppingProductDetailBean [id=" + id + ", product=" + product + ", productTotalPrice="
				+ productTotalPrice + ", productQuantity=" + productQuantity + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + productQuantity;
		result = prime * result + productTotalPrice;
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
		ShoppingProductDetailBean other = (ShoppingProductDetailBean) obj;
		/*if (id != other.id)
			return false;*/
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (productQuantity != other.productQuantity)
			return false;
		if (productTotalPrice != other.productTotalPrice)
			return false;
		return true;
	}
	
}
