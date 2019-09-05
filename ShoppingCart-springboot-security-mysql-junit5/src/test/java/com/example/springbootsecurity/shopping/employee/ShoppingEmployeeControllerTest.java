package com.example.springbootsecurity.shopping.employee;


import static com.example.springbootsecurity.shopping.employee.ShoppingEmployeeTestUtility.getAllShoppingOrderDetails;
import static com.example.springbootsecurity.shopping.employee.ShoppingEmployeeTestUtility.getShoppingOrderDetail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;

//@ExtendWith(MockitoExtension.class) // Already included in @WebMvcTest
@WebMvcTest(ShoppingEmployeeController.class)
@ContextConfiguration(classes=EmployeeConfiguration.class) // @ContextConfiguration defines class-level metadata that is used to determine how to load and configure an ApplicationContext for integration tests.
public class ShoppingEmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ShoppingEmployeeService shoppingEmployeeService;
	
	@WithMockUser
	@Test
	public void showOrdersTest() throws Exception {
		List<ShoppingOrderDetailBean> orders = getAllShoppingOrderDetails();
		when(shoppingEmployeeService.showOrders()).thenReturn(orders);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/emp/showOrders");
				
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().attribute("oList", orders))
				.andExpect(view().name("orders"))
				.andDo(print());
	}
	
	
	@WithMockUser
	@Test
	public void viewOrderDetailsTest() throws Exception {
		ShoppingOrderDetailBean shoppingOrderDetailBean = getShoppingOrderDetail();
		List <ShoppingProductDetailBean> products = shoppingOrderDetailBean.getProducts();
		
		when(shoppingEmployeeService.viewOrderDetails(anyLong())).thenReturn(shoppingOrderDetailBean);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/emp/viewOrderDetails")
				.param("orderId", "1");
		
		mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(model().attribute("pList", products))
				.andExpect(view().name("orderDetails"))
				.andDo(print());
	}
	
}
