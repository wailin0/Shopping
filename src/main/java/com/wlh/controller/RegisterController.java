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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String register(@ModelAttribute("userForm") User userForm,BindingResult bindingResult,HttpServletRequest request, RedirectAttributes att) {
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
		
		att.addFlashAttribute("registerSuccess", "Register sucessful,please check your email for activiation link");
		return "redirect:/login";    //if register success redirect to login page
	}
	
	
	@GetMapping("/badRequest")
	public String BadRequest() {
		return "badRequest";
	}
	
	
	//for verifying email
	@GetMapping("/confirmEmail")
	public String comfirmEmail(@RequestParam("token") String token,RedirectAttributes att) {
		Token verificationToken = tokenService.getToken(token);
		
		//check if token is valid or not
		if(verificationToken == null) {
			att.addFlashAttribute("invalidToken", "invalid token");
			return "redirect:/badRequest";
		}
		
		//check if token was expired or not
		User user = verificationToken.getUser();
		Calendar calender = Calendar.getInstance();
		if(verificationToken.getExpireDate().getTime()-calender.getTime().getTime()<=0) {
			att.addFlashAttribute("expiredToken","Token have been expired");
			return "redirect:/badRequest";
		}
		
		//enable user if above 2 conditions are false
		user.setEnabled(true);
		userService.enableUser(user);
		
		//redirect with success messages
		att.addFlashAttribute("activationSuccess","Your account has been successfully activated!");
		return "redirect:/login";
	}
	
	
	
}

