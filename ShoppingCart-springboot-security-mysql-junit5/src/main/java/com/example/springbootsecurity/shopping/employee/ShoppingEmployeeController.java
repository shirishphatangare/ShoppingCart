package com.example.springbootsecurity.shopping.employee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;


@Controller
public class ShoppingEmployeeController {

	
@Autowired
private ShoppingEmployeeService empService;	



@RequestMapping("/emp/showOrders")
public String showOrders(Model m) {
	List<ShoppingOrderDetailBean> ordersList= empService.showOrders();
	m.addAttribute("oList",ordersList);
	return "orders";
	
}

@RequestMapping("/emp/viewOrderDetails")
public String viewOrderDetails(HttpServletRequest req,Model m) {
	int orderId = Integer.parseInt(req.getParameter("orderId"));
	ShoppingOrderDetailBean order = empService.viewOrderDetails(orderId);
	
	List <ShoppingProductDetailBean> products = order.getProducts();
	m.addAttribute("pList",products);
	return "orderDetails";
}
	
}
