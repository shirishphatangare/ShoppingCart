package com.example.springbootsecurity.shopping.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;

@Repository
public interface ShoppingEmployeeDAO  extends CrudRepository<ShoppingOrderDetailBean, Long>{

}