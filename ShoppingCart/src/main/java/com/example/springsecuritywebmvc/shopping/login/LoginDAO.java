package com.example.springsecuritywebmvc.shopping.login;

import com.example.springsecuritywebmvc.shopping.entity.ShoppingUserBean;

public interface LoginDAO {

	void createUser(ShoppingUserBean newUser);

}