package com.example.springsecuritywebmvc.shopping.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsecuritywebmvc.shopping.entity.ShoppingUserBean;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO logindao;
	

	
	/* (non-Javadoc)
	 * @see com.shopping.login.LoginService#registerNewUser(com.shopping.entity.ShoppingUserBean)
	 */
	
	public boolean registerNewUser(ShoppingUserBean newUser) {
		boolean success = false;
		try {
			logindao.createUser(newUser);
			success = true;
		}catch(Exception e) {
			success = false;
		}
		return success;
	}
	
	

}
