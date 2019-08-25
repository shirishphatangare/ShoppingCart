package com.example.springbootsecurity.shopping.login;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootsecurity.shopping.entity.ShoppingAuthorityBean;
import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

@Repository
public class LoginDAOImpl implements LoginDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	/* (non-Javadoc)
	 * @see com.shopping.login.LoginDAO#createUser(com.shopping.entity.ShoppingUserBean)
	 */
	
	@Transactional
	public void createUser(ShoppingUserBean  newUser) {
		ShoppingAuthorityBean authority = new ShoppingAuthorityBean(newUser.getUserName(),"ROLE_USER");
		Session session = sessionFactory.getCurrentSession();
		session.save(newUser);
		session.save(authority);
	}

}
