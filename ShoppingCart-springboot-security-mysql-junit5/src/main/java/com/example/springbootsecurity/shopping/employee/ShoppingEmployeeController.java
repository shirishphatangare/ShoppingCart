package com.example.springbootsecurity.shopping.employee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;

@Controller
public class ShoppingEmployeeController {

@Autowired
private ShoppingEmployeeService empService;	


	@GetMapping("/emp/showOrders")
	@Secured({"ROLE_EMPLOYEE","ROLE_MANAGER"})
	public String showOrders(Model m) {
		List<ShoppingOrderDetailBean> ordersList= empService.showOrders();
		m.addAttribute("oList",ordersList);
		return "orders";
	}
	
	@GetMapping("/emp/viewOrderDetails")
	@Secured({"ROLE_EMPLOYEE","ROLE_MANAGER"})
	public String viewOrderDetails(HttpServletRequest req,Model m) {
		Long orderId = Long.valueOf(req.getParameter("orderId"));
		ShoppingOrderDetailBean order = empService.viewOrderDetails(orderId);
		
		List <ShoppingProductDetailBean> products = order.getProducts();
		m.addAttribute("pList",products);
		return "orderDetails";
	}
	
}
