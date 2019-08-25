package com.example.springbootsecurity.shopping.user;

import java.util.List;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;

public interface ShoppingUserDAO {

	List<ShoppingProductBean> showAllProducts();

	ShoppingProductBean getProduct(int productId);

	void placeOrder(List<ShoppingProductDetailBean> productsList);

}