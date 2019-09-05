package com.example.springbootsecurity.shopping.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;

@Service
public class ShoppingEmployeeServiceImpl implements ShoppingEmployeeService {
	
	@Autowired
	private ShoppingEmployeeDAO shoppingEmployeeDAO;
	
	public List <ShoppingOrderDetailBean> showOrders() {
		List <ShoppingOrderDetailBean> orderDetails = new ArrayList<ShoppingOrderDetailBean>();
		try {
			shoppingEmployeeDAO.findAll().forEach(orderDetails::add);
		}catch (Exception e) {
			System.out.println("Exception while showing orders: " +  e);
			e.printStackTrace();
		}
		return orderDetails;
	}
	
	
	public ShoppingOrderDetailBean viewOrderDetails(Long orderId){
		Optional<ShoppingOrderDetailBean> orderDetail = null; 
		try {
			orderDetail = shoppingEmployeeDAO.findById(orderId);
		}catch(Exception e) {
			System.out.println("Exception while showing particular order: " +  e);
			e.printStackTrace();
		}
		return orderDetail.get();
	}
	
}
