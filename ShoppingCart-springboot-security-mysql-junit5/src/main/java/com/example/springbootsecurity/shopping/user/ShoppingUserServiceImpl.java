package com.example.springbootsecurity.shopping.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.springbootsecurity.shopping.employee.ShoppingEmployeeDAO;
import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;
import com.example.springbootsecurity.shopping.login.LoginDAO;

@Service
public class ShoppingUserServiceImpl implements ShoppingUserService {
	
	@Autowired
	private ShoppingUserDAO shoppingUserDao;
	
	@Autowired
	private LoginDAO loginDao;
	
	@Autowired
	private ShoppingEmployeeDAO shoppingEmployeeDao;
	
	
	/* (non-Javadoc)
	 * @see com.shopping.user.ShoppingUserService#showAllProducts()
	 */
	
	public List <ShoppingProductBean> showAllProducts() {
		List <ShoppingProductBean> products = new ArrayList<ShoppingProductBean>();
		try {
			Iterable<ShoppingProductBean> iter = shoppingUserDao.findAll();
			iter.forEach(products::add);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return products; 
	}
	
	/* (non-Javadoc)
	 * @see com.shopping.user.ShoppingUserService#getProduct(int)
	 */
	
	public ShoppingProductBean getProduct(long productId) {
		Optional<ShoppingProductBean> product = null;
		try {
			product = shoppingUserDao.findById(productId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return product.get();
	}


	public boolean placeOrder(List <ShoppingProductDetailBean> productsList) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
	    Optional<ShoppingUserBean> user = loginDao.findById(userName);
	    
	    int orderPrice = 0 ;
		ShoppingOrderDetailBean order = new ShoppingOrderDetailBean();
		order.setUser(user.get());
		Date orderDate = new Date();
		order.setOrderDate(orderDate);
		
		Iterator<ShoppingProductDetailBean> iter = productsList.iterator();
		ShoppingProductDetailBean product;
		while(iter.hasNext()) {
			product = iter.next();
			orderPrice = orderPrice + product.getProductTotalPrice();
			order.getProducts().add(product);
		}
		order.setOrderPrice(orderPrice);
		ShoppingOrderDetailBean savedOrder = shoppingEmployeeDao.save(order);
		if(savedOrder.equals(order)) {
			return true;
		}else {
			return false;
		}
		
	}

}
