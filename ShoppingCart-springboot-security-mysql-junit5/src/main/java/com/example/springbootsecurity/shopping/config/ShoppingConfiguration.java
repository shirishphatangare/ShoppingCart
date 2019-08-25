package com.example.springbootsecurity.shopping.config;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan(basePackages= {"com.example.springbootsecurity.shopping.AOP","com.example.springbootsecurity.shopping.user","com.example.springbootsecurity.shopping.employee","com.example.springbootsecurity.shopping.manager","com.example.springbootsecurity.shopping.login","com.example.springbootsecurity.shopping.entity","com.example.springbootsecurity.shopping.config"})
@Import({ShoppingSecurityConfiguration.class})
public class ShoppingConfiguration implements WebMvcConfigurer {
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsps/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/*") // /static/ will be mapped to /resources/css/
        // For e.g. if I give URL as http://localhost:8080/ShoppingCart/static/style.css - 
        // It will be mapped to http://localhost:8080/ShoppingCart/resources/css/style.css
                .addResourceLocations("/resources/css/")
                .addResourceLocations("/resources/js/")// Actual directory structure in classpath - location attribute in xml configuration
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
    }
	
	
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
