package com.example.springbootsecurity.shopping.login;

import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

public interface LoginService {

	boolean registerNewUser(ShoppingUserBean newUser);

}