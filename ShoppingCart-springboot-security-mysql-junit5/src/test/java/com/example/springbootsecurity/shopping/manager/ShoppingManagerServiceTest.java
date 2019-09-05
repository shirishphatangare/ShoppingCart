package com.example.springbootsecurity.shopping.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;

@ExtendWith(MockitoExtension.class) 
class ShoppingManagerServiceTest {

	@Mock
	private ShoppingManagerDAO managerDao;

	@InjectMocks
	private ShoppingManagerServiceImpl managerService;

	@Test
	public void performManagerDaoSave() {
		ShoppingProductBean newProduct = new ShoppingProductBean("Test Product", "Test Author", 100);
		when(managerDao.save(any(ShoppingProductBean.class))).thenReturn(newProduct);
		boolean saveResult = managerService.createProduct(newProduct);
		assertEquals(true, saveResult);
	}
	
	
	@Test
	public void performManagerDaoSaveException() {
		ShoppingProductBean newProduct = new ShoppingProductBean("Test Product", "Test Author", 100);
		when(managerDao.save(any(ShoppingProductBean.class))).thenThrow(new RuntimeException());
		boolean saveResult = managerService.createProduct(newProduct);
		assertEquals(false, saveResult);
	}

}
