package com.example.springbootsecurity.shopping.employee;

import java.util.List;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;

public interface ShoppingEmployeeService {

	List<ShoppingOrderDetailBean> showOrders();

	ShoppingOrderDetailBean viewOrderDetails(Long orderId);

}