package com.example.demo.entity;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserPreferences {
	
 private String timeZoneId = "default";
 private Map<Long,Integer> shoppingCart = new ConcurrentHashMap<Long,Integer>();
 
 public String getTimeZoneId() {
	 return timeZoneId;
 }
 
 
 
 public void setTimeZoneId(String timeZoneId) {
	 this.timeZoneId = timeZoneId;
 }
 
 
	
	public Map<Long,Integer> getProducts() {
		return shoppingCart;
	}
	
	
	public void addToCart() {
		shoppingCart.put(2L, 200);
	}
	
//	public void updateCart(long productId,int quantity) {
//		shoppingCart.put(productId, quantity);
//	}
//	
//	public void removeFromCart(long productId) {
//		shoppingCart.remove(productId);
//	}
//	
//	public void emptyCart() {
//		shoppingCart.clear();
//	}
	
	public Iterator<Long> getCartIterator() {
		return shoppingCart.keySet().iterator();
	}
	
	public int getProductQuantity(long productId) {
		return shoppingCart.get(productId);
	}
	

 
 
 
}