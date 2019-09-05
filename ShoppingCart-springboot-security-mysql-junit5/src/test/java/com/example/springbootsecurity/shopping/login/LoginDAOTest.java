package com.example.springbootsecurity.shopping.login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;

@ActiveProfiles("dev")
@DataJpaTest // Not hitting actual DB even when @ActiveProfiles("prod")
class LoginDAOTest {

	@Autowired
	private LoginDAO loginRespository;
	
	
	@Test
	void performLogindaoSave() {
		ShoppingUserBean newUser = new ShoppingUserBean("TestUsername", "TestPassword", "Test Firstname","Test Lastname", "usertest@email.com");
		ShoppingUserBean savedUser = loginRespository.save(newUser);
		assertEquals(newUser,savedUser);
	}
	
	
	@Test
	void performLogindaoSaveNegative() {
		ShoppingUserBean newUser = new ShoppingUserBean("TestUsername", "TestPassword", "Test Firstname","Test Lastname", "usertest@email.com");
		ShoppingUserBean savedUser = loginRespository.save(newUser);
		assertEquals(new ShoppingUserBean("Negative TestUsername", "TestPassword", "Test Firstname","Test Lastname", "usertest@email.com"),savedUser);
	}


}
