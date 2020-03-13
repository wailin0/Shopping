//for registration,enabling user


package com.wlh.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wlh.model.User;
import com.wlh.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	
	public void registerUser(User user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(false);
		user.setRole("USER");
		userRepo.save(user);	
	}

	public void enableUser(User user) {
		userRepo.save(user);
	}
	
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

	
}
