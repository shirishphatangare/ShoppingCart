package com.shopping.user;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.entity.ShoppingProductBean;

@Repository
public class ShoppingUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List <ShoppingProductBean> showProducts() {
			Session session = sessionFactory.getCurrentSession();
			
			//try {
				List<ShoppingProductBean> products = session.createQuery("From ShoppingProductBean").getResultList();
				return products;
			//}finally {
				//session.close();
			//}
	}

}
