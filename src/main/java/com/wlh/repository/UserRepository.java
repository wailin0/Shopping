package com.wlh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wlh.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	
	Users findByUsername(String username);
	Users findByEmail(String email);
	void deleteById(int id);
	
}
