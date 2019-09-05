package com.example.springbootsecurity.shopping.entity;

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

	private Map<Long,Integer> shoppingBasket = new ConcurrentHashMap<Long,Integer>();
	
	public Map<Long,Integer> getProducts() {
		return shoppingBasket;
	}
	
	
	public void addToCart(long productId,int quantity) {
		int currentProductQuantity;
		if(shoppingBasket.get(productId) == null) {
			currentProductQuantity = 0;
		}else {
			currentProductQuantity = shoppingBasket.get(productId);
		}
		shoppingBasket.put(productId, (currentProductQuantity + quantity));
	}
	
	public void updateCart(long productId,int quantity) {
		shoppingBasket.put(productId, quantity);
	}
	
	public void removeFromCart(long productId) {
		shoppingBasket.remove(productId);
	}
	
	public void emptyCart() {
		shoppingBasket.clear();
	}
	
	public Iterator<Long> getCartIterator() {
		return shoppingBasket.keySet().iterator();
	}
	
	public int getProductQuantity(long productId) {
		return shoppingBasket.get(productId);
	}
	

}
