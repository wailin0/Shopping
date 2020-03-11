package com.wlh.model;


import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.wlh.model.User;

@Entity
public class Token {
	
	private static final int Expiration = 60*24;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	private String token;
	
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	private Date expireDate;



	private Date calculateExpiryDate(int expireInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expireInMinutes);
		return new Date(cal.getTime().getTime());
	}
	
	
	   public Token() {
	        super();
	    }

	   public Token(String token, User user) {
	        super();

	        this.token = token;
	        this.user = user;
	        this.expireDate = calculateExpiryDate(Expiration);
	    }
	
	
	
	
	
	
	
	
	
	public static int getExpiration() {
		return Expiration;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Date getExpireDate() {
		return expireDate;
	}


	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}




}
