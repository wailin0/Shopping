package com.wlh.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wlh.model.User;
import com.wlh.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
	
	Token findByUser(User user);
	Token findByToken(String token);
	
	
	void deleteByExpireDate(Date date);
	
	

}
