package com.example.springbootsecurity.shopping.user;

import java.util.List;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;

public interface ShoppingUserService {

	List<ShoppingProductBean> showAllProducts();

	ShoppingProductBean getProduct(long productId);

	boolean placeOrder(List<ShoppingProductDetailBean> productsList);

}