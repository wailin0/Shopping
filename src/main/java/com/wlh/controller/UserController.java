package com.wlh.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wlh.model.Users;
import com.wlh.repository.UserRepository;
import com.wlh.service.UserService;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	
	@GetMapping("/info")
	public Users getUserList(Principal principal) { 
	return userRepo.findByEmail(principal.getName());
	}

	@PostMapping("/info")
    public Users createOrSaveUser(@RequestBody Users user) {
        return userRepo.save(user);
    }
	
	@GetMapping("/info/{id}")
	public  Users getUser(@PathVariable Long id) {
		Users user = userRepo.findById(id).get();
		return user;
	}

	@PutMapping("/info/{id}")
	public Users UpdateUser(@RequestBody Users newuser,@PathVariable Long id) {
		return userRepo.findById(id).map(user -> {
			user.setUsername(newuser.getUsername());
			user.setEmail(newuser.getEmail());
			user.setPhone(newuser.getPhone());
			user.setEnabled(newuser.isEnabled());
			return userRepo.save(user);
        }).orElseGet(() -> {
            newuser.setId(id);
            return userRepo.save(newuser);
        });
    }

	@DeleteMapping("/info/{id}")
	public void deleteCompany(@PathVariable Long id) {
		userRepo.deleteById(id);
	}

}
