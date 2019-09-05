package com.example.springbootsecurity.shopping.login;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class LoginControllerSecurityTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders
			  .webAppContextSetup(context)
			  .apply(springSecurity()) // .addFilters(springSecurityFilterChain) or standalone setup can also be done
			  .build();
	}

	// Testing login functionality with Form Based Authentication
	@Test
	public void performFormLoginActualCredentials() throws Exception {
		mvc.perform(formLogin("/authenticateLogin").user("UserE").password("User123"))
				.andExpect(authenticated().withUsername("UserE").withRoles("MANAGER"))
				.andExpect((redirectedUrl("/shoppingHome")));
	}

	
	@Test
	public void performFormLoginInvalidCredentials() throws Exception {
		mvc.perform(formLogin("/authenticateLogin").user("UserZ").password("Password"))
				.andExpect(unauthenticated())
				.andExpect((redirectedUrl("/login?error")));
	}

	// Testing HTTP Basic Authentication
	// mvc.perform(get("/").with(httpBasic("user","password")))
	
	
	@Test
	// @WithMockUser // Here we just need to hit secured URL with a mocked user. We
	// do not need to test for security. Without this annotation will get redirected
	// to /login page. Mainly used in Method security
	public void performShowProductsWithMockedUser() throws Exception {
		mvc.perform(get("/showProducts").with(testUserRole())) // Actual user may not exist in DB. Mocking user to bypass security
     // mvc.perform(get("/showProducts")) // Uncomment this with @WithMockUser annotation
	   .andExpect(status().isOk())
	   .andExpect(view().name("productsList"));
	}
	
	
	@Test
	@WithMockUser(roles= {"USER1"}) // @WithMockUser and with() method accepts any username and password but need valid role parameter but do not have to be authorized role, just valid role (Here either USER,MANAGER OR EMPLOYEE)
	public void performShowProductsWithMockedUserAnnotation() throws Exception {
		//mvc.perform(get("/showProducts").with(testUserRole())) // Actual user may not exist in DB. Mocking user to bypass security
        mvc.perform(get("/showProducts")) // Uncomment this with @WithMockUser annotation
	   .andExpect(status().isOk())
	   .andExpect(view().name("productsList"));
	}
	
	
	
	// Testing Method Security
	@Test
	@WithMockUser(roles= {"EMPLOYEE"}) 
	public void performShowOrdersWithMockedUserAnnotation() throws Exception {
		//mvc.perform(get("/mgr/createProduct").with(testEmployeeRole())) 
        mvc.perform(get("/emp/showOrders")) // Uncomment this with @WithMockUser annotation
	   .andExpect(status().isOk())
	   .andExpect(view().name("orders"));
	}
	
	
	@Test
	//@WithMockUser(roles= {"MANAGER"}) 
	public void performCreateProductsWithMockedUser() throws Exception {
		mvc.perform(get("/mgr/createProduct").with(testManagerRole())) 
        //mvc.perform(get("/mgr/createProduct")) // Uncomment this with @WithMockUser annotation
	   .andExpect(status().isOk())
	   .andExpect(view().name("createProduct"));
	}
	


	// Testing with CSRF Protection
	@Test
	public void givenNoCsrfthenForbidden() throws Exception {
		mvc.perform(post("/")
				.with(csrf()
				.useInvalidToken()))
		        .andExpect(status().isForbidden());
	}

	
	@Test
	public void givenCsrf_thenOk() throws Exception {
		mvc.perform(post("/")
				.with(csrf()))
		        .andExpect((redirectedUrl("/login")));
	}


	// Testing logout
	@Test
	public void performLogout() throws Exception {
		mvc.perform(logout()) // logout is a static import from SecurityMockMvcRequestBuilders
				.andExpect((redirectedUrl("/login?logout")));
	}

	
	protected RequestPostProcessor testUserRole() { 
		return 	user("user").password("userPass").roles("USER"); 
	}
	
	
	protected RequestPostProcessor testEmployeeRole() { 
		return 	user("user").password("userPass").roles("EMPLOYEE"); 
	}
	
	
	protected RequestPostProcessor testManagerRole() { 
		return 	user("user").password("userPass").roles("MANAGER"); 
	}
	 
}
