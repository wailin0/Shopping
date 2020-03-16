//for registration,enabling user


package com.wlh.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wlh.model.Users;
import com.wlh.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	
	public void registerUser(Users user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(false);
		user.setRole("USER");
		userRepo.save(user);	
	}

	public void enableUser(Users user) {
		userRepo.save(user);
	}
	

	public Users findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	
	public Users findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public Users findById(Long id) {
		return userRepo.findById(id).get();
	}
	
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	public Users save(Users user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	
}
