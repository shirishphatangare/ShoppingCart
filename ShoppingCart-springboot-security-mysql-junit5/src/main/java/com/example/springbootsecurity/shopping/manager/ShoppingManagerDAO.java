package com.example.springbootsecurity.shopping.manager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;

@Repository
public interface ShoppingManagerDAO  extends CrudRepository<ShoppingProductBean,Long>{

}