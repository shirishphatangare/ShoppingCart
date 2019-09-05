package com.example.springbootsecurity.shopping.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;

@Repository
public interface ShoppingUserDAO extends CrudRepository<ShoppingProductBean, Long> {

}