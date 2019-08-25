package com.example.springbootsecurity.shopping.user;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

@Repository
public class ShoppingUserDAOImpl implements ShoppingUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.shopping.user.ShoppingUserDAO#showAllProducts()
	 */
	
	@Transactional
	public List <ShoppingProductBean> showAllProducts() {
		Session session = sessionFactory.getCurrentSession();
		List<ShoppingProductBean> products = null;
		products = session.createQuery("From ShoppingProductBean").getResultList();
		return products;
	}

	
	/* (non-Javadoc)
	 * @see com.shopping.user.ShoppingUserDAO#getProduct(int)
	 */
	
	@Transactional
	public ShoppingProductBean getProduct(int productId) {
		Session session = sessionFactory.getCurrentSession();
		ShoppingProductBean product = null;
		product = (ShoppingProductBean)session.get(ShoppingProductBean.class, productId);
		return product;
	}
	
	
	/* (non-Javadoc)
	 * @see com.shopping.user.ShoppingUserDAO#placeOrder(java.util.List)
	 */
	
	@Transactional
	public void placeOrder(List <ShoppingProductDetailBean> productsList) {
		Session session = sessionFactory.getCurrentSession();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userName = auth.getName(); 
	    ShoppingUserBean user = (ShoppingUserBean) session.get(ShoppingUserBean.class, userName);
	    int orderPrice = 0 ;
		ShoppingOrderDetailBean order = new ShoppingOrderDetailBean();
		order.setUser(user);
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
		session.save(order);
	}
	
}
