package com.example.springbootsecurity.shopping.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;

@ActiveProfiles("dev")
@DataJpaTest // Not hitting actual DB even when @ActiveProfiles("prod")
class ShoppingManageDAOTest {

	@Autowired
	private ShoppingManagerDAO shoppingManagerRespository;
	
	
	@Test
	void shoppingManagerDaoSave() {
		ShoppingProductBean newProduct = new ShoppingProductBean("Test Product", "Test Author", 100);
		ShoppingProductBean savedProduct = shoppingManagerRespository.save(newProduct);
		assertEquals(newProduct,savedProduct);
	}
	
	
	@Test
	void shoppingManagerDaoSaveNegative() {
		ShoppingProductBean newProduct = new ShoppingProductBean("Test Product", "Test Author", 100);
		ShoppingProductBean savedProduct = shoppingManagerRespository.save(newProduct);
		assertEquals(new ShoppingProductBean("Negative Test Product", "Test Author", 100),savedProduct);
	}


}
