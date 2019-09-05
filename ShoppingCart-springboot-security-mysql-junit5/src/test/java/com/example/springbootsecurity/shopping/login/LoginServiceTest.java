package com.example.springbootsecurity.shopping.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

@ExtendWith(MockitoExtension.class) 
class LoginServiceTest {

	@Mock
	private LoginDAO loginDao;

	@InjectMocks
	private LoginServiceImpl loginService;

	@Test
	public void performRegisterNewUserService() {
		ShoppingUserBean newUser = new ShoppingUserBean("TestUsername", "TestPassword", "Test Firstname","Test Lastname", "usertest@email.com");
		when(loginDao.save(any(ShoppingUserBean.class))).thenReturn(newUser);
		boolean saveResult = loginService.registerNewUser(newUser);
		assertEquals(true, saveResult);
	}
	
	
	@Test
	public void performRegisterNewUserServiceException() {
		ShoppingUserBean newUser = new ShoppingUserBean("TestUsername", "TestPassword", "Test Firstname","Test Lastname", "usertest@email.com");
		when(loginDao.save(any(ShoppingUserBean.class))).thenThrow(new RuntimeException());
		boolean saveResult = loginService.registerNewUser(newUser);
		assertEquals(false, saveResult);
	}

}
