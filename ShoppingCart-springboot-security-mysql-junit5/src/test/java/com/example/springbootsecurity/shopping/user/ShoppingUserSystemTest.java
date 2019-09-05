package com.example.springbootsecurity.shopping.user;

import static org.hamcrest.Matchers.hasValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@ActiveProfiles("prod") // Test with actual Mysql DB
@Tag("System")
@AutoConfigureMockMvc
@Disabled
class ShoppingUserSystemTest {

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
	
	
	@WithMockUser (username="User1",roles={"USER","EMPLOYEE","MANAGER"})  
	@Test
	public void performAddToCartAndShowCartDifferentSession() throws Exception {
		MockHttpSession mocksession = new MockHttpSession();
		
		long productId = 6L; 
		int productQuantity = 200;
		
		// Add 1 product to the shopping cart
		RequestBuilder request1 = MockMvcRequestBuilders
				.post("/addToCart")
				.session(mocksession) // Need to add MockHttpSession for session scoped bean ShoppingCartBean
				.with(csrf())
				.param("productId", String.valueOf(productId))
				.param("productQuantity", String.valueOf(productQuantity));
		
		mockMvc.perform(request1)
	    .andExpect(status().isOk()) ;
		
		// Place an order with 1 product in the shopping cart
		RequestBuilder request2 = MockMvcRequestBuilders
				.get("/showCart")
				.session(new MockHttpSession()); // Not using same session, so expecting model attribute pList to be empty ConcurrentHashMap
		
		Map<ShoppingProductBean,Integer> emptyProductsList = new ConcurrentHashMap<ShoppingProductBean,Integer>(); // Empty ConcurrentHashMap
		mockMvc.perform(request2)
				.andExpect(status().isOk())
				.andExpect(model().attribute("pList", emptyProductsList)) // Expect an empty ConcurrentHashMap
				.andExpect(view().name("Cart"))
				.andDo(print());
	}
	
	
	
	@WithMockUser (username="User1",roles={"USER","EMPLOYEE","MANAGER"})  
	@Test
	public void performAddToCartAndShowCartSameSession() throws Exception {
		MockHttpSession mocksession = new MockHttpSession();
		
		long productId = 6L; 
		int productQuantity = 200;
		
		// Add 1 product to the shopping cart
		RequestBuilder request1 = MockMvcRequestBuilders
				.post("/addToCart")
				.session(mocksession) // Need to add MockHttpSession for session scoped bean ShoppingCartBean
				.with(csrf())
				.param("productId", String.valueOf(productId))
				.param("productQuantity", String.valueOf(productQuantity));
		
		mockMvc.perform(request1)
	    .andExpect(status().isOk()) ;
		
		// Place an order with 1 product in the shopping cart
		RequestBuilder request2 = MockMvcRequestBuilders
				.get("/showCart")
				.session(mocksession); // Using same session, so expecting model attribute pList NOT to be empty
				
		mockMvc.perform(request2)
				.andExpect(status().isOk())
				.andExpect(model().attribute("pList", hasValue(200))) // Expect a Map with at least one value of 200
				.andExpect(view().name("Cart"))
				.andDo(print());
	}

	
	
	@WithMockUser (username="User1",roles={"USER","EMPLOYEE","MANAGER"})  
	@Test
	public void performAddToCartAndPalceOrderSameSession() throws Exception {
		MockHttpSession mocksession = new MockHttpSession();
		
		long productId = 6L; 
		int productQuantity = 200;
		
		// Add 1 product to the shopping cart
		RequestBuilder request1 = MockMvcRequestBuilders
				.post("/addToCart")
				.session(mocksession) // Need to add MockHttpSession for session scoped bean ShoppingCartBean
				.with(csrf())
				.param("productId", String.valueOf(productId))
				.param("productQuantity", String.valueOf(productQuantity));
		
		mockMvc.perform(request1)
	    .andExpect(status().isOk()) ;
		
		// Place an order with 1 product in the shopping cart
		RequestBuilder request2 = MockMvcRequestBuilders
				.post("/placeOrder")
				.session(mocksession) // Using same session
				.with(csrf());
				
		mockMvc.perform(request2)
				.andExpect(status().isOk()) 
				.andExpect(model().attribute("orderSize", 1))
				.andExpect(model().attribute("orderSuccess", true))
				.andExpect(view().name("checkOut"));
	}
	
	
}
