package com.shopping.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.ShoppingProductBean;

@Service
public class ShoppingUserService {
	
	@Autowired
	private ShoppingUserDAO sud;
	
	public List <ShoppingProductBean> showProducts() {
		
		return sud.showProducts();
		
	}

}
