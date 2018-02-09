package com.shopping.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginservice;

@RequestMapping({"/Login","/"})
public String showLogin() {
	
	return "Login";
}

@RequestMapping("/register")
public String showRegister() {
	
	return "register";
}


@RequestMapping("/shoppingHome")
public String showHome() {
	
	return "shoppingHome";
}


@RequestMapping(value = "/authenticateLogin", method= RequestMethod.POST)
public void authenticateLogin() {
	/*String userName = request.getParameter("username");
	String password = request.getParameter("password");
	//if(loginservice.authenticateLogin(userName,password))
	if(true)	
		return "shoppingHome";
	else
		return "Login";
		*/
	System.out.println("Inside authenticateLogin");
}	



	
	
}
