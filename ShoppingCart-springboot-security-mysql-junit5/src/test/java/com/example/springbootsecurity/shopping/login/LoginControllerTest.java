package com.example.springbootsecurity.shopping.login;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;


@ExtendWith(MockitoExtension.class) 
@WebMvcTest(LoginController.class)
@ContextConfiguration(classes=LoginConfiguration.class)
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LoginService loginService;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@WithMockUser
	@Test
	public void viewRegistrationPage() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/register");
		
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(view().name("register"));
	}
	
	
	@WithMockUser
	@Test
	public void performRegisterNewUserSuccess() throws Exception {
		
		when(loginService.registerNewUser(any(ShoppingUserBean.class))).thenReturn(true);
		when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("$2a$10$xvmMWIpaC74kOpkFLMlCiuU4rVZghX.6L42EL9F0NGqxxKfVcwG/K");

		RequestBuilder request = MockMvcRequestBuilders
				.post("/registerNewUser")
				.with(csrf())
				.param("firstname", "Test Firstname")
				.param("lastname", "Test Lastname")
				.param("enterEmail", "usertest@email.com")
		        .param("enterUser", "TestUsername")
		        .param("enterPass", "TestPassword");
				
		
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().attribute("usercreatedsuccess", true))
				.andExpect(view().name("Login"))
				.andDo(print());

	}
	
	
	@WithMockUser
	@Test
	public void performRegisterNewUserFailure() throws Exception {
		
		when(loginService.registerNewUser(any(ShoppingUserBean.class))).thenReturn(false);
		when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("$2a$10$xvmMWIpaC74kOpkFLMlCiuU4rVZghX.6L42EL9F0NGqxxKfVcwG/K");

		RequestBuilder request = MockMvcRequestBuilders
				.post("/registerNewUser")
				.with(csrf())
				.param("firstname", "Test Firstname")
				.param("lastname", "Test Lastname")
				.param("enterEmail", "usertest@email.com")
		        .param("enterUser", "TestUsername")
		        .param("enterPass", "TestPassword");
				
		
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().attribute("usercreatedsuccess", false))
				.andExpect(view().name("Login"))
				.andDo(print());

	}
	

}
