package com.shopping.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.shopping.user","com.shopping.login","com.shopping.entity","com.shopping.config"})
@Import({ShoppingSecurityConfiguration.class})
public class ShoppingConfiguration {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsps/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
