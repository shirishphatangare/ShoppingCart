package com.example.springbootsecurity.shopping.login;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

@Repository
public interface LoginDAO extends CrudRepository<ShoppingUserBean, String> {

}