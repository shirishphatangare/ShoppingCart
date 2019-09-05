package com.example.springbootsecurity.shopping.employee;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@ActiveProfiles("dev") // Test with in-memory H2 DB
@Tag("Integration")
class ShoppingEmployeeIntegrationTest {

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
	
	
	@WithMockUser(roles= {"EMPLOYEE"})
	@Test
	public void showOrdersIntegration() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/emp/showOrders");
				
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().hasNoErrors() )
				.andExpect(view().name("orders"))
				.andDo(print());
	}
	
	

	
	@WithMockUser(roles= {"EMPLOYEE","MANAGER"})
	@Test
	public void viewOrderDetailsIntegration() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/emp/viewOrderDetails")
				.param("orderId", "1");
				
		
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().hasNoErrors())
				.andExpect(view().name("orderDetails"))
				.andDo(print());
	}
	
	
	@WithMockUser(roles= {"EMPLOYEE","MANAGER"})
	@Test
	public void viewOrderDetailsIntegrationException() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/emp/viewOrderDetails")
				.param("orderId", "2"); // orderid '2' is not present in H2 DB so expect NestedServletException
				
		
		assertThrows(NestedServletException.class, () -> mockMvc.perform(request));
	}
	
	
	@WithMockUser(roles= {"USER"}) // role USER is forbidden
	@Test
	public void viewOrderDetailsIntegrationForbidden() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/emp/viewOrderDetails")
				.param("orderId", "1");
				
		
		mockMvc.perform(request)
				.andExpect(status().isForbidden()); 
	}
}
