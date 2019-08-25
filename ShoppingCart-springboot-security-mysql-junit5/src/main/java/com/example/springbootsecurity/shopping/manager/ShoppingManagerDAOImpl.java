package com.example.springbootsecurity.shopping.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;

@Repository
public class ShoppingManagerDAOImpl implements ShoppingManagerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	/* (non-Javadoc)
	 * @see com.shopping.manager.ShoppingManagerDAO#createProduct(com.shopping.entity.ShoppingProductBean)
	 */
	
	@Transactional
	public void createProduct(ShoppingProductBean newProduct) {
		Session session = sessionFactory.getCurrentSession();
		session.save(newProduct);
	}
}
