package com.example.springbootsecurity.shopping.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;

@Service
public class ShoppingManagerServiceImpl implements ShoppingEmployeeService {
	
	@Autowired
	private ShoppingManagerDAO shoppingManagerDao;
	
	
	public boolean createProduct(ShoppingProductBean product) {
		boolean success = false;
		try {
			ShoppingProductBean newProduct = shoppingManagerDao.save(product);
			if(newProduct.equals(product)) {
				success = true;
			}
		}catch(Exception e) {
			success = false;
		}
		return success;
	}
}
