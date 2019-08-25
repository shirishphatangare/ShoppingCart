package com.example.springbootsecurity.shopping.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;

@Service
public class ShoppingManagerServiceImpl implements ShoppingManagerService {
	
	@Autowired
	private ShoppingManagerDAO smd;
	
	
	/* (non-Javadoc)
	 * @see com.shopping.manager.ShoppingManagerService#createProduct(com.shopping.entity.ShoppingProductBean)
	 */
	
	public boolean createProduct(ShoppingProductBean product) {
		boolean success = false;
		try {
			smd.createProduct(product);
			success = true;
		}catch(Exception e) {
			success = false;
		}
		return success;
	}
}
