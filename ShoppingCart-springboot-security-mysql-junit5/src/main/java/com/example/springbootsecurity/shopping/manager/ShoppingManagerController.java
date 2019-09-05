package com.example.springbootsecurity.shopping.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;


@Controller
public class ShoppingManagerController {

	@Autowired
	private ShoppingEmployeeService managerService;	


	@GetMapping("/mgr/createProduct")
	@Secured({"ROLE_MANAGER"})
	public String createProduct() {
		return "createProduct";
	}
	
	
	@PostMapping("/mgr/submitNewProduct")
	@Secured({"ROLE_MANAGER"})
	public String submitNewProduct(HttpServletRequest request, Model m) {
		String productName = request.getParameter("productName");
		String productAuthor = request.getParameter("productAuthor");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		ShoppingProductBean product = new ShoppingProductBean(productName, productAuthor, productPrice);
		boolean createProductSuccess = managerService.createProduct(product);
		m.addAttribute("createProductSuccess",createProductSuccess);
		return "createProduct";
	}

}
