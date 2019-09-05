package com.example.springbootsecurity.shopping.user;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.springbootsecurity.shopping.entity.ShoppingCartBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;

//@ExtendWith(MockitoExtension.class) // Included in @WebMvcTest
@WebMvcTest(ShoppingUserController.class)
@ContextConfiguration(classes=UserConfiguration.class)
public class ShoppingUserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ShoppingUserService shoppingUserService;	

	@SpyBean
	private ShoppingCartBean shoppingCart;
	

	@WithMockUser
	@Test
	public void performAddtoCart() throws Exception {
		Long productId = 4L;
		int productQuantity = 4;
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/addToCart")
				.with(csrf())
				.param("productId", String.valueOf(productId))
				.param("productQuantity", String.valueOf(productQuantity));
		
		mockMvc.perform(request);
		assertEquals(productQuantity,shoppingCart.getProductQuantity(productId));
	}
	
	
	@WithMockUser
	@Test
	public void performUpdateCart() throws Exception {
		Long productId = 4L;
		int productQuantity = 14;
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/updateCartQuantity")
				.with(csrf())
				.param("productId", String.valueOf(productId))
				.param("productQuantity", String.valueOf(productQuantity));
		
		mockMvc.perform(request);
		assertEquals(productQuantity,shoppingCart.getProductQuantity(productId));
	}
	
	
	
	@WithMockUser
	@Test
	public void performRemoveFromCart() throws Exception {
		Long productId = 4L;
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/removeFromCart")
				.with(csrf())
				.param("productId", String.valueOf(productId));
		
		mockMvc.perform(request);
		assertEquals(0,shoppingCart.getProducts().size());
	}
	
	
	
	@WithMockUser
	@Test
	public void performPlaceOrder() throws Exception {
		shoppingCart.addToCart(5, 4);
		shoppingCart.addToCart(6, 7);
		
		ShoppingProductBean shoppingProductBean1 = new ShoppingProductBean("Test Product1", "Test Author1", 100);
		ShoppingProductBean shoppingProductBean2 = new ShoppingProductBean("Test Product2", "Test Author2", 200);

		when(shoppingUserService.getProduct(5)).thenReturn(shoppingProductBean1);
		when(shoppingUserService.getProduct(6)).thenReturn(shoppingProductBean2);
		when(shoppingUserService.placeOrder(Mockito.<ShoppingProductDetailBean>anyList())).thenReturn(true);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/placeOrder")
				.with(csrf());
				
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().attribute("orderSize", 2))
				.andExpect(model().attribute("orderSuccess", true))
				.andExpect(view().name("checkOut"))
				.andDo(print());
	}
	
	
	@WithMockUser
	@Test
	public void performPlaceOrderNegative() throws Exception {
		shoppingCart.addToCart(5, 4);
		shoppingCart.addToCart(6, 7);
		
		ShoppingProductBean shoppingProductBean1 = new ShoppingProductBean("Test Product1", "Test Author1", 100);
		ShoppingProductBean shoppingProductBean2 = new ShoppingProductBean("Test Product2", "Test Author2", 200);

		when(shoppingUserService.getProduct(5)).thenReturn(shoppingProductBean1);
		when(shoppingUserService.getProduct(6)).thenReturn(shoppingProductBean2);
		when(shoppingUserService.placeOrder(Mockito.<ShoppingProductDetailBean>anyList())).thenReturn(false);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/placeOrder")
				.with(csrf());
				
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().attribute("orderSuccess", false))
				.andExpect(view().name("checkOut"))
				.andDo(print());
	}
	
}
