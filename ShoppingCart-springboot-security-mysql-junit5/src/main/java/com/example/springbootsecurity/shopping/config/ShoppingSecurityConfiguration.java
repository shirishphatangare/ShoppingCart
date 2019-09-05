package com.example.springbootsecurity.shopping.config;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ShoppingSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passwordEncoder());
	}

	
	
	RequestMatcher csrfRequestMatcher = new RequestMatcher() {
		  // Always allow the HTTP GET method
		  private Pattern allowedMethods = Pattern.compile("^GET$");
		  
		  // Disable CSFR protection on the following urls:
		  private AntPathRequestMatcher[] requestMatchers = {
		    new AntPathRequestMatcher("/h2-console/**") // disable csrf check for h2-console endpoint
		  };

		  @Override
		  public boolean matches(HttpServletRequest request) {
		    // Skip allowed methods
		    if (allowedMethods.matcher(request.getMethod()).matches()) {
		      return false;
		    }   

		    // If the request match one url the CSFR protection will be disabled
		    for (AntPathRequestMatcher rm : requestMatchers) {
		      if (rm.matches(request)) { return false; }
		    }

		    return true;
		  } 
		};
    
    
	
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		.antMatchers("/register*").permitAll()
		.antMatchers("/js/*","/css/*").permitAll()
		.antMatchers("/h2-console/**").permitAll() // Allow h2-console endpoint
		.antMatchers("/**").authenticated()
		.anyRequest().authenticated()
		.and().headers().frameOptions().disable() // Disable X-Frame-Options in Spring Security. Without this line h2-console won't render (get error - localhost refused to connect)
		.and().formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/authenticateLogin").defaultSuccessUrl("/shoppingHome")
		.permitAll()
		.and().logout().permitAll().and()
		.rememberMe();
		
		 http.csrf().requireCsrfProtectionMatcher(csrfRequestMatcher); // disable csrf check selectively  for h2-console endpoint
 
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
