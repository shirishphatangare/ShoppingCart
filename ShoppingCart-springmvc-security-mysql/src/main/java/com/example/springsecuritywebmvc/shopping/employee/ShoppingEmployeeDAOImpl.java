package com.example.springsecuritywebmvc.shopping.employee;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsecuritywebmvc.shopping.entity.ShoppingOrderDetailBean;

@Repository
public class ShoppingEmployeeDAOImpl implements ShoppingEmployeeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.shopping.employee.ShoppingEmployeeDAO#showOrders()
	 */
	
	@Transactional
	public List <ShoppingOrderDetailBean> showOrders() {
		Session session = sessionFactory.getCurrentSession();
		List<ShoppingOrderDetailBean> orders = null;
		orders = session.createQuery("From ShoppingOrderDetailBean").getResultList();
		return orders;
	}

	/* (non-Javadoc)
	 * @see com.shopping.employee.ShoppingEmployeeDAO#viewOrderDetails(int)
	 */
	
	@Transactional
	public ShoppingOrderDetailBean viewOrderDetails(int orderId){
		Session session = sessionFactory.getCurrentSession();
		ShoppingOrderDetailBean order = null;
     	order = session.get(ShoppingOrderDetailBean.class, orderId);
		return order;
	}
		
}
