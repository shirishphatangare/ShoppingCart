package com.example.springbootsecurity.shopping.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginservice;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String showIndex() {
		return "index";
	}
	
	
	@GetMapping("/login")
	public String showLogin() {
		return "Login";
	}
	
	@GetMapping({"/register"})
	public String showRegister() {
		return "register";
	}
	
	@PostMapping("/registerNewUser")
	public String registerNewUser(HttpServletRequest request,Model m) {
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("enterEmail");
		String userName = request.getParameter("enterUser");
		String password = passwordEncoder.encode(request.getParameter("enterPass"));
	
		ShoppingUserBean newUser = new ShoppingUserBean(userName, password, firstName, lastName, email);
		if(loginservice.registerNewUser(newUser)) {
			m.addAttribute("usercreatedsuccess",true);
		}else {
			m.addAttribute("usercreatedsuccess",false);
		}
		
		return "Login";
	}
	
	
	@GetMapping("/shoppingHome")
	public String showHome() {
		return "shoppingHome";
	}
	
	
	// Below code is commented because "/authenticateLogin" is provided internally by Spring
	/*@RequestMapping(value = "/authenticateLogin", method= RequestMethod.POST)
	public void authenticateLogin() {
		/*String userName = request.getParameter("username");
		String password = request.getParameter("password");
		//if(loginservice.authenticateLogin(userName,password))
		if(true)	
			return "shoppingHome";
		else
			return "Login";
			
		System.out.println("Inside authenticateLogin");
	}*/	
	

}
