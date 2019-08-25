package com.example.springbootsecurity.shopping.login;

import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

public interface LoginDAO {

	void createUser(ShoppingUserBean newUser);

}