package com.example.springbootsecurity.shopping.manager;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Hitting actual DB when @ActiveProfiles("prod")
@ActiveProfiles("prod") // Test with real DB
@Tag("System")
@Disabled
class ShoppingManagerSystemTest { 

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
	@WithMockUser (roles= {"USER"}) // Fails with role "USER"
	public void performSubmitNewProductNegativeRole() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/mgr/submitNewProduct")
				.with(csrf())
				.param("productName", "Test Product")
				.param("productAuthor", "Test Author")
				.param("productPrice", "100");
				
		
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().attribute("createProductSuccess", true))
				.andExpect(view().name("createProduct"))
				.andDo(print());	
		}
	
	
	@Test
	@WithMockUser (roles= {"MANAGER"})  // Pass with role "MANAGER"
	public void performSubmitNewProductPositiveRole() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/mgr/submitNewProduct")
				.with(csrf())
				.param("productName", "Test Product")
				.param("productAuthor", "Test Author")
				.param("productPrice", "100");
				
		
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().attribute("createProductSuccess", true))
				.andExpect(view().name("createProduct"))
				.andDo(print());	
		}
	
	
	
	@Test
	@WithMockUser (roles= {"MANAGER"})  // Fails with invalid csrf token
	public void performSubmitNewProductInvalidCsrf() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/mgr/submitNewProduct")
				.with(csrf().useInvalidToken())
				.param("productName", "Test Product")
				.param("productAuthor", "Test Author")
				.param("productPrice", "100");
				
		
		mockMvc.perform(request)
				.andExpect(status().isForbidden()) ;
		}
	
}
