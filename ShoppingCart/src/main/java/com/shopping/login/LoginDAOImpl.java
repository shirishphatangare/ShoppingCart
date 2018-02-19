package com.shopping.login;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.shopping.entity.ShoppingUserBean;

@Repository
public class LoginDAO {
	
	
	private Session getSession() {
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(ShoppingUserBean.class)
				.buildSessionFactory();								 					 

		return factory.getCurrentSession();
	}
	
	
	public boolean isAuthenticated(String userName,String password) {
		Session session = getSession();
		try {
			
			session.beginTransaction();
			String dbPassword =(String) session.createQuery("select s.password from ShoppingUserBean s  where s.userName='" + userName +"'").getSingleResult() ;
			
			session.getTransaction().commit();
			
			if(password.equals(dbPassword))
				return true;
			else
				return false;
		} catch(NoResultException e) {
	        return false;
	    } finally {
			// TODO: handle finally clause
			session.close();
		}
	}

}
