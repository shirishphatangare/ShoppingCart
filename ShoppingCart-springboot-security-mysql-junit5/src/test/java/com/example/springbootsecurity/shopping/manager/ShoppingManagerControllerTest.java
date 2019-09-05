package com.example.springbootsecurity.shopping.manager;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;

//@ExtendWith(MockitoExtension.class) // Included in @WebMvcTest
@WebMvcTest(ShoppingManagerController.class)
@ContextConfiguration(classes=ManagerConfiguration.class)
public class ShoppingManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ShoppingEmployeeService managerService;
	
	@WithMockUser
	@Test
	public void performCreateProduct() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/mgr/createProduct");
		
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(view().name("createProduct"));
	}
	
	
	@WithMockUser
	@Test
	public void performSubmitNewProductProduct() throws Exception {
		
		when(managerService.createProduct(any(ShoppingProductBean.class))).thenReturn(true);
		
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
	
	@WithMockUser
	@Test
	public void performSubmitNewProductProductNegative() throws Exception {
		
		when(managerService.createProduct(any(ShoppingProductBean.class))).thenReturn(false);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/mgr/submitNewProduct")
				.with(csrf())
				.param("productName", "Test Product")
				.param("productAuthor", "Test Author")
				.param("productPrice", "100");
				
		
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().attribute("createProductSuccess", false))
				.andExpect(view().name("createProduct"))
				.andDo(print());
	}
	
}
