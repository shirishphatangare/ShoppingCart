package com.example.springbootsecurity.shopping.entity;

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
	@Column(name="productid")
	private Long id;

	@Column(name="productname")
	private String productName;
	
	@Column(name="productauthor")
	private String productAuthor;

	@Column(name="productprice")
	private int productPrice;
	
	public ShoppingProductBean() {
		
	}
	
	public ShoppingProductBean(String productName, String productAuthor, int productPrice) {
		super();
		this.productName = productName;
		this.productAuthor = productAuthor;
		this.productPrice = productPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((productAuthor == null) ? 0 : productAuthor.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + productPrice;
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
		ShoppingProductBean other = (ShoppingProductBean) obj;
		/*if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;*/
		if (productAuthor == null) {
			if (other.productAuthor != null)
				return false;
		} else if (!productAuthor.equals(other.productAuthor))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productPrice != other.productPrice)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ShoppingProductBean [id=" + id + ", productName=" + productName + ", productAuthor=" + productAuthor
				+ ", productPrice=" + productPrice + "]";
	}
	
}
