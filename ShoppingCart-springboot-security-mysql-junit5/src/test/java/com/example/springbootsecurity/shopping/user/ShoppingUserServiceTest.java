package com.example.springbootsecurity.shopping.user;

import static com.example.springbootsecurity.shopping.user.ShoppingUserTestUtility.getAllShoppingProductDetails;
import static com.example.springbootsecurity.shopping.user.ShoppingUserTestUtility.getAllShoppingProducts;
import static com.example.springbootsecurity.shopping.user.ShoppingUserTestUtility.getShoppingOrderDetail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.springbootsecurity.shopping.employee.ShoppingEmployeeDAO;
import com.example.springbootsecurity.shopping.entity.ShoppingOrderDetailBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;
import com.example.springbootsecurity.shopping.entity.ShoppingUserBean;
import com.example.springbootsecurity.shopping.login.LoginDAO;

@ExtendWith(MockitoExtension.class) 
class ShoppingUserServiceTest {

	@Mock
	private ShoppingUserDAO shoppingUserDAO;
	
	@Mock
	private ShoppingEmployeeDAO shoppingEmployeeDao;
	
	@Mock
	private LoginDAO loginDao;

	@InjectMocks
	private ShoppingUserServiceImpl userService;

	@Test
	public void performShowProductsService() {
		List<ShoppingProductBean> productDetails = getAllShoppingProducts();
		when(shoppingUserDAO.findAll()).thenReturn(productDetails);

		List<ShoppingProductBean> saveResult = userService.showAllProducts();
		assertIterableEquals(productDetails, saveResult); 
	}
	
	
	@Test
	public void performGetProductService() {
		Long orderId = 1L;
		ShoppingProductBean ShoppingProductBean = new ShoppingProductBean("Test Product1", "Test Author1", 100);
		
		when(shoppingUserDAO.findById(anyLong())).thenReturn(Optional.of(ShoppingProductBean));
		ShoppingProductBean saveResult = userService.getProduct(orderId);
		
		assertEquals(ShoppingProductBean, saveResult); 
	}
	
	@Test
	public void performPlaceOrderService() { // Removed id as an equality criteria from entity objects - ShoppingProductBean, ShoppingProductDetailBean and ShoppingOrderDetailBean for this test
		ShoppingUserBean shoppingUser = new ShoppingUserBean("TestUsername", "TestPassword", "Test Firstname","Test Lastname", "usertest@email.com");
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);
		
		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication); // method in when should be invoked on mocked object
		when(authentication.getName()).thenReturn("TestUsername");
		when(loginDao.findById(anyString())).thenReturn(Optional.of(shoppingUser));
		when(shoppingEmployeeDao.save(any(ShoppingOrderDetailBean.class))).thenReturn(getShoppingOrderDetail());
		
		boolean saveResult = userService.placeOrder(getAllShoppingProductDetails());
		assertTrue(saveResult);
	}
	
}
