package com.shopping.entity;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;


/*This Bean is used for Shopping Cart */

@Component
//@Scope("session")
public class ShoppingCartBean {

	private Map<Integer,Integer> products = new ConcurrentHashMap<Integer,Integer>();
	
	public Map<Integer,Integer> getProducts() {
		return products;
	}
	public void setProducts(Map<Integer,Integer> products) {
		this.products = products;
	}
	
}
