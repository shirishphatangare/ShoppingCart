package com.example.springsecuritywebmvc.shopping.employee;

import java.util.List;

import com.example.springsecuritywebmvc.shopping.entity.ShoppingOrderDetailBean;

public interface ShoppingEmployeeDAO {

	List<ShoppingOrderDetailBean> showOrders();

	ShoppingOrderDetailBean viewOrderDetails(int orderId);

}