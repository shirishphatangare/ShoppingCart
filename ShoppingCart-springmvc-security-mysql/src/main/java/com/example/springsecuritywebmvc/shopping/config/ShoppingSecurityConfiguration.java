package com.example.springsecuritywebmvc.shopping.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages= {"com.example.springsecuritywebmvc.shopping.config"})
public class ShoppingSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passwordEncoder());
	}

	
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		.antMatchers("/register*").permitAll()
		.antMatchers("/static/*").permitAll()
		.antMatchers("/**").hasAnyRole("USER","EMPLOYEE","MANAGER")
		.antMatchers("/emp/**").hasAnyRole("EMPLOYEE","MANAGER")
		.antMatchers("/mgr/**").hasRole("MANAGER")
		.anyRequest().authenticated().and()
		.formLogin()
		.loginPage("/Login")
		.loginProcessingUrl("/authenticateLogin").defaultSuccessUrl("/shoppingHome")
		.permitAll().and()
		.logout().permitAll().and()
		.rememberMe();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
