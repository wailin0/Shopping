package com.wlh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlh.model.Users;
import com.wlh.model.Token;
import com.wlh.repository.TokenRepository;

@Service
public class VerificationService {

	@Autowired
	TokenRepository tokenRepo;
	
	public Token findByComfirmationToken(String comfirmationToken) {
		return tokenRepo.findByToken(comfirmationToken);
	}
	
	public Token findByUser(Users user) {
		return tokenRepo.findByUser(user);
	
	}

}
