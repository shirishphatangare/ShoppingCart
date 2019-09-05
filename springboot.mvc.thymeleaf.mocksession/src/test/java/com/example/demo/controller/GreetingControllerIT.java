package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;


//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes=ScopeConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@AutoConfigureMockMvc
//@WebAppConfiguration
class GreetingControllerIT {

	@Autowired
	 private WebApplicationContext wac;
	 
	 private MockMvc mockMvc;
	 
//	 @BeforeEach
//	 public void setup() {
//	  this.mockMvc = webAppContextSetup(this.wac)
//			  .build();
//	 }
	 
	 @BeforeEach
		public void setup() {
			mockMvc = MockMvcBuilders
				  .webAppContextSetup(wac)
				  .apply(springSecurity()) // .addFilters(springSecurityFilterChain) or standalone setup can also be done
				  .build();
		}
	
	@WithMockUser
	@Test
	 public void testSessionScope() throws Exception {
	  MockHttpSession mocksession = new MockHttpSession();
	  this.mockMvc.perform(
	    post("/greeting")
	    .with(csrf())
	      .session(mocksession))
	  .andExpect(status().isOk()) ;
	    //.andExpect(model().attribute("timeZone", "US/Pacific"));
	 
	  this.mockMvc.perform(
	    post("/home")
	    .with(csrf())
	     .session(mocksession))
	  .andExpect(status().isOk()) ;
	    //.andExpect(model().attribute("timeZone", "US/Pacific"));
	 
	  
	  assertThrows(NestedServletException.class,
	           () -> this.mockMvc.perform(
	        		    post("/home")
	        		    .with(csrf())
	        		     .session(new MockHttpSession()))
	           );
	  
	  
	     
	    //.andExpect(model().attribute("timeZone", "default"));
	 }

}
