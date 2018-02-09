package com.shopping.entity;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;


@Component
//@Scope("session")
public class ShoppingCartBean {

	//private List<Integer> products = new ArrayList<Integer>();
	//private int productsQuantity = 1;
	//private Hashtable<Integer,Integer> products = new Hashtable<Integer,Integer>();
	private Map<Integer,Integer> products = new ConcurrentHashMap<Integer,Integer>();
	
	public Map<Integer,Integer> getProducts() {
		return products;
	}
	public void setProducts(Map<Integer,Integer> products) {
		this.products = products;
	}
	
}
