package com.example.springsecuritywebmvc.shopping.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCDispatcherServletInitializer extends
AbstractAnnotationConfigDispatcherServletInitializer {
	
	
	   protected Class<?>[] getRootConfigClasses() {
	      return null;
	   }
	 
	   
	   protected Class<?>[] getServletConfigClasses() {
	      return new Class[] { ShoppingConfiguration.class };
	   }
	 
	   
	   protected String[] getServletMappings() {
	      return new String[] { "/" };
	   }
}	

