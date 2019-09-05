package com.example.springbootsecurity.shopping.employee;

import static com.example.springbootsecurity.shopping.employee.ShoppingEmployeeTestUtility.getAllShoppingOrderDetails;
import static com.example.springbootsecurity.shopping.employee.ShoppingEmployeeTestUtility.getShoppingOrderDetail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;

@ExtendWith(MockitoExtension.class) 
class ShoppingEmployeeServiceTest {

	@Mock
	private ShoppingEmployeeDAO shoppingEmployeeDAO;

	@InjectMocks
	private ShoppingEmployeeServiceImpl employeeService;

	@Test
	public void showOrdersService() {
		List <ShoppingOrderDetailBean> orderDetails = getAllShoppingOrderDetails();
		when(shoppingEmployeeDAO.findAll()).thenReturn(getAllShoppingOrderDetails());
		List<ShoppingOrderDetailBean> saveResult = employeeService.showOrders();
		assertIterableEquals(orderDetails, saveResult); // started working - when id is removed from equality criteria
		
		assertAll(
				() -> assertEquals(3,saveResult.get(0).getProducts().get(0).getProductQuantity()),
				() -> assertEquals(1000,saveResult.get(0).getProducts().get(1).getProductTotalPrice())
				);
	}
	
	
	@Test
	public void viewOrderDetailsService() {
		Long orderId = 1L;
		when(shoppingEmployeeDAO.findById(anyLong())).thenReturn(Optional.of(getShoppingOrderDetail()));
		ShoppingOrderDetailBean saveResult = employeeService.viewOrderDetails(orderId);
		
		assertAll(
				() -> assertEquals(5,saveResult.getProducts().get(1).getProductQuantity()),
				() -> assertEquals(300,saveResult.getProducts().get(0).getProductTotalPrice())
				);
	}
	
}
