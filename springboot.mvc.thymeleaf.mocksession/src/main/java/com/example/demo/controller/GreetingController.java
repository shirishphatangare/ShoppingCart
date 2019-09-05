package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.UserPreferences;

@Controller
public class GreetingController {

	@Autowired private UserPreferences userPreferences;
	
	@PostMapping("/greeting")
    public String greeting(@RequestParam(name="username", required=false, defaultValue="World") String username, Model model) {
    	userPreferences.setTimeZoneId("US/Pacific");
    	System.out.println("Inside greeting:" +  userPreferences.getTimeZoneId());
    	userPreferences.addToCart();
    	
    	//model.addAttribute("username", username);
    	//model.addAttribute("timeZone", userPreferences.getTimeZoneId());
        return "greeting";
    }
    
    
    
    @PostMapping(value="/home")
    public String goToPage(Model model) {
    	//String zone = userPreferences.getTimeZoneId();
    	System.out.println("Inside home:" + userPreferences.getTimeZoneId());
    	System.out.println(userPreferences.getProductQuantity(2));
     //model.addAttribute("timeZone", userPreferences.getTimeZoneId());
     return "home";
    }
}