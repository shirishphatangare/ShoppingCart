package com.example.springsecuritywebmvc.shopping.user;

import java.util.List;

import com.example.springsecuritywebmvc.shopping.entity.ShoppingProductBean;
import com.example.springsecuritywebmvc.shopping.entity.ShoppingProductDetailBean;

public interface ShoppingUserDAO {

	List<ShoppingProductBean> showAllProducts();

	ShoppingProductBean getProduct(int productId);

	void placeOrder(List<ShoppingProductDetailBean> productsList);

}