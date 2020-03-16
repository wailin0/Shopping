//token creation class for email confirmation,password reset token 

package com.wlh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlh.model.Users;
import com.wlh.model.Token;
import com.wlh.repository.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepo;
	
	
	public void createMailConfirmationToken(Users user, String token) {
		Token myToken = new Token(token,user);
		tokenRepo.save(myToken);
	}
	
	public Token getToken(String token) {
		return tokenRepo.findByToken(token);
	}

}
