package com.example.springsecuritywebmvc.shopping.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsecuritywebmvc.shopping.entity.ShoppingOrderDetailBean;

@Service
public class ShoppingEmployeeServiceImpl implements ShoppingEmployeeService {
	
	@Autowired
	private ShoppingEmployeeDAO sed;
	
	/* (non-Javadoc)
	 * @see com.shopping.employee.ShoppingEmployeeService#showOrders()
	 */
	
	public List <ShoppingOrderDetailBean> showOrders() {
		List <ShoppingOrderDetailBean> orderDetails = null;
		try {
			orderDetails = sed.showOrders();
		}catch (Exception e) {
			
		}
		return orderDetails;
	}
	
	/* (non-Javadoc)
	 * @see com.shopping.employee.ShoppingEmployeeService#viewOrderDetails(int)
	 */
	
	public ShoppingOrderDetailBean viewOrderDetails(int orderId){
		ShoppingOrderDetailBean orderDetail = null; 
		try {
			orderDetail = sed.viewOrderDetails(orderId);
		}catch(Exception e) {
			
		}
		return orderDetail;
	}
	
}
