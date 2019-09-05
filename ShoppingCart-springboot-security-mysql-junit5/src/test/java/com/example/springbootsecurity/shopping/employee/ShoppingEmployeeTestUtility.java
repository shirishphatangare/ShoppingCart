package com.example.springbootsecurity.shopping.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

public class ShoppingEmployeeTestUtility {

	public static List<ShoppingOrderDetailBean> getAllShoppingOrderDetails() {
		List<ShoppingOrderDetailBean> orders = new ArrayList<ShoppingOrderDetailBean>();
		
		ShoppingOrderDetailBean shoppingOrderDetailBean1 = new ShoppingOrderDetailBean();
		
		// Create List of ShoppingProductDetailBean
		List<ShoppingProductDetailBean> products = new ArrayList<ShoppingProductDetailBean>();
		
		ShoppingProductDetailBean shoppingProductDetailBean1 = new ShoppingProductDetailBean();
		shoppingProductDetailBean1.setId(1);
		shoppingProductDetailBean1.setProduct(new ShoppingProductBean("Test Product", "Test Author", 100));
		shoppingProductDetailBean1.setProductQuantity(3);
		shoppingProductDetailBean1.setProductTotalPrice(300);
		
		ShoppingProductDetailBean shoppingProductDetailBean2 = new ShoppingProductDetailBean();
		shoppingProductDetailBean2.setId(2);
		shoppingProductDetailBean2.setProduct(new ShoppingProductBean("Test Product1", "Test Author2", 200));
		shoppingProductDetailBean2.setProductQuantity(5);
		shoppingProductDetailBean2.setProductTotalPrice(1000);
 
		products.add(shoppingProductDetailBean1);
		products.add(shoppingProductDetailBean2);
		
		// Create first shoppingOrderDetailBean
		shoppingOrderDetailBean1.setId(1L);
		shoppingOrderDetailBean1.setOrderDate(new Date());
		shoppingOrderDetailBean1.setOrderPrice(1300);
		shoppingOrderDetailBean1.setProducts(products);
		shoppingOrderDetailBean1.setUser(new ShoppingUserBean("TestUsername", "TestPassword", "Test Firstname","Test Lastname", "usertest@email.com"));
		
		orders.add(shoppingOrderDetailBean1);
		return orders;
	}
	
	public static ShoppingOrderDetailBean getShoppingOrderDetail() {
		ShoppingOrderDetailBean shoppingOrderDetailBean = new ShoppingOrderDetailBean();
		
		// Create List of ShoppingProductDetailBean
		List<ShoppingProductDetailBean> products = new ArrayList<ShoppingProductDetailBean>();
		
		ShoppingProductDetailBean shoppingProductDetailBean1 = new ShoppingProductDetailBean();
		shoppingProductDetailBean1.setId(1);
		shoppingProductDetailBean1.setProduct(new ShoppingProductBean("Test Product", "Test Author", 100));
		shoppingProductDetailBean1.setProductQuantity(3);
		shoppingProductDetailBean1.setProductTotalPrice(300);
		
		ShoppingProductDetailBean shoppingProductDetailBean2 = new ShoppingProductDetailBean();
		shoppingProductDetailBean2.setId(2);
		shoppingProductDetailBean2.setProduct(new ShoppingProductBean("Test Product1", "Test Author2", 200));
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
