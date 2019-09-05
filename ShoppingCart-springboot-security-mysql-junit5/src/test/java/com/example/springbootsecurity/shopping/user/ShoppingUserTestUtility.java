package com.example.springbootsecurity.shopping.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

public class ShoppingUserTestUtility {

	public static List <ShoppingProductBean> getAllShoppingProducts() {
		List<ShoppingProductBean> products = new ArrayList<ShoppingProductBean>();
		
		ShoppingProductBean ShoppingProductBean1 = new ShoppingProductBean("Test Product1", "Test Author1", 100);
		ShoppingProductBean ShoppingProductBean2 = new ShoppingProductBean("Test Product2", "Test Author2", 200);
		
		products.add(ShoppingProductBean1);
		products.add(ShoppingProductBean2);
		return products;
	}
	
	public static List <ShoppingProductDetailBean> getAllShoppingProductDetails() {
		List<ShoppingProductDetailBean> products = new ArrayList<ShoppingProductDetailBean>();
		
		ShoppingProductDetailBean shoppingProductDetailBean1 = new ShoppingProductDetailBean();
		ShoppingProductBean ShoppingProductBean1 = new ShoppingProductBean("Test Product1", "Test Author1", 100);
		shoppingProductDetailBean1.setProduct(ShoppingProductBean1);
		shoppingProductDetailBean1.setProductQuantity(3);
		shoppingProductDetailBean1.setProductTotalPrice(300);
		
		ShoppingProductDetailBean shoppingProductDetailBean2 = new ShoppingProductDetailBean();
		ShoppingProductBean ShoppingProductBean2 = new ShoppingProductBean("Test Product2", "Test Author2", 200);
		shoppingProductDetailBean2.setProduct(ShoppingProductBean2);
		shoppingProductDetailBean2.setProductQuantity(5);
		shoppingProductDetailBean2.setProductTotalPrice(1000);
		
		
		products.add(shoppingProductDetailBean1);
		products.add(shoppingProductDetailBean2);
		
		return products;
	}
	
	
	public static ShoppingOrderDetailBean getShoppingOrderDetail() {
		ShoppingOrderDetailBean shoppingOrderDetailBean = new ShoppingOrderDetailBean();
		
		// Create List of ShoppingProductDetailBean
		List<ShoppingProductDetailBean> products = new ArrayList<ShoppingProductDetailBean>();
		
		ShoppingProductDetailBean shoppingProductDetailBean1 = new ShoppingProductDetailBean();
		shoppingProductDetailBean1.setId(0);
		shoppingProductDetailBean1.setProduct(getAllShoppingProducts().get(0));
		shoppingProductDetailBean1.setProductQuantity(3);
		shoppingProductDetailBean1.setProductTotalPrice(300);
		
		ShoppingProductDetailBean shoppingProductDetailBean2 = new ShoppingProductDetailBean();
		shoppingProductDetailBean2.setId(1);
		shoppingProductDetailBean2.setProduct(getAllShoppingProducts().get(1));
		shoppingProductDetailBean2.setProductQuantity(5);
		shoppingProductDetailBean2.setProductTotalPrice(1000);
 
		products.add(shoppingProductDetailBean1);
		products.add(shoppingProductDetailBean2);
		
		// Create first shoppingOrderDetailBean
		shoppingOrderDetailBean.setId(1L);
		shoppingOrderDetailBean.setOrderDate(new Date());
		shoppingOrderDetailBean.setOrderPrice(1300);
		shoppingOrderDetailBean.setProducts(products);
		shoppingOrderDetailBean.setUser(new ShoppingUserBean("TestUsername", "TestPassword", "Test Firstname","Test Lastname", "usertest@email.com"));
		
		return shoppingOrderDetailBean;
	}
	
	
}
