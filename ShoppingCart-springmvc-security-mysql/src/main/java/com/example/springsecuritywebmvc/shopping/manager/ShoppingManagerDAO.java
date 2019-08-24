package com.example.springsecuritywebmvc.shopping.manager;

import com.example.springsecuritywebmvc.shopping.entity.ShoppingProductBean;

public interface ShoppingManagerDAO {

	void createProduct(ShoppingProductBean newProduct);

}