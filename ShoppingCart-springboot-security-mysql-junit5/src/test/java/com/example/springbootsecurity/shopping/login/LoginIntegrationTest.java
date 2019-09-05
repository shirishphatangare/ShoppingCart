package com.example.springbootsecurity.shopping.login;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Hitting actual DB when @ActiveProfiles("prod")
@ActiveProfiles("dev") // // Test with in-memory H2 DB
@Tag("Integration")
class LoginIntegrationTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
			  .webAppContextSetup(context)
			  .apply(springSecurity()) // .addFilters(springSecurityFilterChain) or standalone setup can also be done
			  .build();
	}
	
	
	@Test
	public void performRegisterNewUserSuccess() throws Exception {
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
	
	
	@Test
	public void performRegisterNewUserInvalidCsrf() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/registerNewUser")
				.with(csrf().useInvalidToken())
				.param("firstname", "Test Firstname")
				.param("lastname", "Test Lastname")
				.param("enterEmail", "usertest@email.com")
		        .param("enterUser", "TestUsername")
		        .param("enterPass", "TestPassword");
		
		mockMvc.perform(request)
				.andExpect(status().isForbidden()) ;
		}
	
}
