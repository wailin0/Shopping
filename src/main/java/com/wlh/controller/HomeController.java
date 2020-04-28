package com.wlh.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"/","/index","/home"})
	public String home() {
		return "index";
	}
	
    @GetMapping("/shop")
    public String store(){
        return "shop";
    }
    
    
    
    
    @GetMapping("/faq")
    public String faq(){
        return "faq";
    }
    
    
//    @GetMapping("/collection")
//    public String collection(){
//        return "not yet ttttttttttttttttt";
//    }
//    
  
    
    @GetMapping("/contact")
    public String about(){
        return "contact";
    }
    

}
