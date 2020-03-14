package com.wlh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping({"/user","/user/account"})
	public String account() {
		return "user/account";
	}
	
	@GetMapping("/user/security")
	public String security() {
		return "user/security";
	}

}
