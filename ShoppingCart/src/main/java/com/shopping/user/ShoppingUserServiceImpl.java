package com.shopping.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.ShoppingProductBean;
import com.shopping.entity.ShoppingProductDetailBean;

@Service
public class ShoppingUserServiceImpl implements ShoppingUserService {
	
	@Autowired
	private ShoppingUserDAO shoppingUserDAO;
	
	/* (non-Javadoc)
	 * @see com.shopping.user.ShoppingUserService#showAllProducts()
	 */
	@Override
	public List <ShoppingProductBean> showAllProducts() {
		List <ShoppingProductBean> products = null;
		try {
			products = shoppingUserDAO.showAllProducts();
		}catch(Exception e) {
			
		}
		return products; 
	}
	
	/* (non-Javadoc)
	 * @see com.shopping.user.ShoppingUserService#getProduct(int)
	 */
	@Override
	public ShoppingProductBean getProduct(int productId) {
		ShoppingProductBean product = null;
		try {
			product = shoppingUserDAO.getProduct(productId);
		}catch(Exception e) {
			
		}
		return product;
	}

	/* (non-Javadoc)
	 * @see com.shopping.user.ShoppingUserService#placeOrder(java.util.List)
	 */
	@Override
	public void placeOrder(List <ShoppingProductDetailBean> productsList) {
		try {
			shoppingUserDAO.placeOrder(productsList);
		}catch (Exception e) {
			
		}
	}
}
