//controller for registration,email confirmation

package com.wlh.controller;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wlh.service.UserService;
import com.wlh.component.UserValidator;
import com.wlh.component.VerificationMailSender;
import com.wlh.model.User;
import com.wlh.model.Token;
import com.wlh.service.TokenService;

@Controller
public class RegisterController {
	
	@Autowired
	private VerificationMailSender verificationMailSender;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserValidator validator;

	//for getting register page
	@GetMapping("/register")
	public String regster(Model model) {
		model.addAttribute("userForm", new User());
		
		return "register";
	}
	
	
	//saving request for userinfo
	@PostMapping("/register")
	public String register(@ModelAttribute("userForm") User userForm,BindingResult bindingResult,HttpServletRequest request) {
		validator.validate(userForm, bindingResult);
		
		//if theres error while registering reload page
		if(bindingResult.hasErrors()) {
			return "register";
		}

		userService.registerUser(userForm);      //register userinfo into databasse
		String token = UUID.randomUUID().toString();     //generate random token number
		tokenService.createMailConfirmationToken(userForm, token);  //save token into database
		
		//get url and token 
		String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/confirmEmail?token=" + token;
		
		SimpleMailMessage email = verificationMailSender.confirmationMail(appUrl, userForm);  //get email contents from VerificationMailSender class
		mailSender.send(email);            //send verification mail to user
		
		return "redirect:/login";    //if register success redirect to login page
	}
	
	
	
	//for verifying email
	@GetMapping("/confirmEmail")
	public String comfirmEmail(Model model,@RequestParam("token") String token) {
		Token verificationToken = tokenService.getToken(token);
		
		//check if token is valid or not
		if(verificationToken == null) {
			model.addAttribute("message","Invalid token");
			return "redirect:/badRequest";
		}
		
		//check if token was expired or not
		User user = verificationToken.getUser();
		Calendar calender = Calendar.getInstance();
		if(verificationToken.getExpireDate().getTime()-calender.getTime().getTime()<=0) {
			model.addAttribute("message","Token have been expired");
			return "redirect:/access-denied";
		}
		
		//enable user if above 2 conditions are false
		user.setEnabled(true);
		userService.enableUser(user);
		
		model.addAttribute("activationSuccess","Your account has been activated!");
		return "redirect:/login";
	}
	
	
	
}

