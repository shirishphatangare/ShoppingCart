package com.shopping.entity;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


/*This Bean is used for Shopping Cart */

@Component
@Scope(value="session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartBean {

	private Map<Integer,Integer> shoppingCart = new ConcurrentHashMap<Integer,Integer>();
	
	public Map<Integer,Integer> getProducts() {
		return shoppingCart;
	}
	
	
	public void addToCart(int productId,int quantity) {
		int currentProductQuantity;
		if(shoppingCart.get(productId) == null) {
			currentProductQuantity = 0;
		}else {
			currentProductQuantity = shoppingCart.get(productId);
		}
		shoppingCart.put(productId, (currentProductQuantity + quantity));
	}
	
	public void updateCart(int productId,int quantity) {
		shoppingCart.put(productId, quantity);
	}
	
	public void removeFromCart(int productId) {
		shoppingCart.remove(productId);
	}
	
	public void emptyCart() {
		shoppingCart.clear();
	}
	
	public Iterator<Integer> getCartIterator() {
		return shoppingCart.keySet().iterator();
	}
	
	public int getProductQuantity(int productId) {
		return shoppingCart.get(productId);
	}
	

}
