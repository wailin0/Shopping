//class for sending email


package com.wlh.component;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.wlh.model.User;


@Component
public class VerificationMailSender {
	

	
	public SimpleMailMessage confirmationMail(String link,User user ) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Email Confirmation");
		mail.setText("\n Please click the link below to verify your email \n" + link);
		mail.setFrom("noreply@mail.com");
		
		return mail;
	}
}
