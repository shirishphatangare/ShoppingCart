package com.example.springbootsecurity.shopping.user;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;

@ActiveProfiles("dev")
@DataJpaTest // Not hitting actual DB even when @ActiveProfiles("prod"). Solution is to use @AutoConfigureTestDatabase(replace=Replace.NONE). 
// This ensures that @DataJpaTest do not replace your configured datasource by an it's own in-memory data source.
@AutoConfigureTestDatabase(replace=Replace.NONE) // Default: org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.ANY
class ShoppingUserDAOTest {

	@Autowired
	private ShoppingUserDAO shoppingUserRespository;
	
	
	@Test
	void performShoppingUserdaofindAll() {
		List <ShoppingProductBean> expectedProducts = new ArrayList<ShoppingProductBean>();
		ShoppingProductBean ShoppingProductBean1 = new ShoppingProductBean("Core Java", "Ram Tripathi" , 100);
		ShoppingProductBean ShoppingProductBean2 = new ShoppingProductBean("J2EE Basics", "Tata Bajari" , 200);
		ShoppingProductBean ShoppingProductBean3 = new ShoppingProductBean("Java Design Patterns", "Rushi Kapoor", 300);
		
		expectedProducts.add(ShoppingProductBean1);
		expectedProducts.add(ShoppingProductBean2);
		expectedProducts.add(ShoppingProductBean3);
		
		List <ShoppingProductBean> actualProducts = new ArrayList<ShoppingProductBean>();
		shoppingUserRespository.findAll().forEach(actualProducts::add);
		assertIterableEquals(expectedProducts, actualProducts);
	}
	
	
	@Test
	void performShoppingUserdaofindById() {
		Long orderId = 7L;
		Optional <ShoppingProductBean> products = shoppingUserRespository.findById(orderId);
		
		assertAll(
				() -> assertEquals("J2EE Basics", products.get().getProductName()),
				() -> assertEquals(200, products.get().getProductPrice())
			);
	}
	
	
	@Test
	void performShoppingUserdaofindByIdEmpty() {
		Long orderId = 3L; // Non-existing orderId. Expect empty Optional
		Optional <ShoppingProductBean> orderDetails = shoppingUserRespository.findById(orderId);
		
		assertEquals(Optional.empty(), orderDetails);
	}
	
}
