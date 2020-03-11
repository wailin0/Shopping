//for login

package com.wlh.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wlh.model.User;
import com.wlh.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		
		 if (user != null) {
	            Set<GrantedAuthority> authorities = new HashSet<>();
	            if (Objects.equals(username, "admin")) {
	                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	            }else {
	                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	            }
	            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	        }else{
	            throw new UsernameNotFoundException("User " + username + " not found!");
	        }
	}

}
