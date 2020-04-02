package com.wlh.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wlh.model.Users;
import com.wlh.repository.UserRepository;

@RestController
public class UserRestController {
	
	@Autowired
	private UserRepository userRepo;

	
	//get current login user info
	@GetMapping("/info")
	public Users getUserList(Principal principal) { 
	return userRepo.findByEmail(principal.getName());
	}

	
	//save users
	@PostMapping("/info")
    public Users createOrSaveUser(@RequestBody Users user) {
        return userRepo.save(user);
    }
	
	//find all registered users (for testing only)
	@GetMapping("/all")
	public  List<Users> getAllUser() {
		List<Users> user = userRepo.findAll();
		return user;
	}
	
	//get a user by id
	@GetMapping("/info/{id}")
	public  Users getUser(@PathVariable Long id) {
		Users user = userRepo.findById(id).get();
		return user;
	}

	//update user details
	@PutMapping("/info/{id}")
	public Users UpdateUser(@RequestBody Users updateuser,@PathVariable Long id) {
	     Users user = userRepo.findById(id).get();
	     
            user.setUsername(updateuser.getUsername());
            Users updatedUser = userRepo.save(user);   
            
            return updatedUser;
    }

	
	//delete user by id
	@DeleteMapping("/info/{id}")
	public void deleteCompany(@PathVariable Long id) {
		userRepo.deleteById(id);
	}

}
