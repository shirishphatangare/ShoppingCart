package com.example.springbootsecurity.shopping.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO logindao;
	
	public boolean registerNewUser(ShoppingUserBean newUser) {
		boolean success = false;
		try {
			ShoppingUserBean savedUser = logindao.save(newUser);
			if(savedUser.equals(newUser)) {
				success = true;
			}
		}catch(Exception e) {
			success = false;
		}
		return success;
	}
	
}
