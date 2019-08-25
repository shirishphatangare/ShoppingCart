package com.example.springbootsecurity.shopping.employee;

import java.util.List;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;

public interface ShoppingEmployeeDAO {

	List<ShoppingOrderDetailBean> showOrders();

	ShoppingOrderDetailBean viewOrderDetails(int orderId);

}