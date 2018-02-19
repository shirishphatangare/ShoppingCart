package com.shopping.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	@Autowired
	private LoginDAO logindao;
	

	
	public boolean authenticateLogin(String userName, String password) {
		
		return logindao.isAuthenticated(userName, password);
		
	}	

}
