package com.example.springbootsecurity.shopping.employee;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;

@ActiveProfiles("dev")
@DataJpaTest // Not hitting actual DB even when @ActiveProfiles("prod"). Solution is to use @AutoConfigureTestDatabase(replace=Replace.NONE). 
// This ensures that @DataJpaTest do not replace your configured datasource by an it's own in-memory data source.
@AutoConfigureTestDatabase(replace=Replace.NONE) // Default: org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.ANY
class ShoppingEmployeeDAOTest {

	@Autowired
	private ShoppingEmployeeDAO shoppingEmployeeDao;
	
	
	@Test
	void employeedaofindAll() {
		List <ShoppingOrderDetailBean> orderDetails = new ArrayList<ShoppingOrderDetailBean>();
		shoppingEmployeeDao.findAll().forEach(orderDetails::add);
		
		assertAll(
				() -> assertEquals(600, orderDetails.get(0).getProducts().get(2).getProductTotalPrice()),
				() -> assertEquals(1700, orderDetails.get(0).getOrderPrice())
			);
	}

	
	@Test
	void employeedaofindById() {
		Long orderId = 1L;
		Optional <ShoppingOrderDetailBean> orderDetails = shoppingEmployeeDao.findById(orderId);
		
		assertAll(
				() -> assertEquals(600, orderDetails.get().getProducts().get(2).getProductTotalPrice()),
				() -> assertEquals(1700, orderDetails.get().getOrderPrice())
			);
	}
	
	
	@Test
	void employeedaofindByIdEmpty() {
		Long orderId = 2L; // Non-existing orderId. Expect empty Optional
		Optional <ShoppingOrderDetailBean> orderDetails = shoppingEmployeeDao.findById(orderId);
		
		assertEquals(Optional.empty(), orderDetails);
	}
	
}
