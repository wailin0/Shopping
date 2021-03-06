//entity class for user which include login and user info 

package com.wlh.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class Users {

	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id", nullable = false, updatable = false)
		private Long id;
		private String username;
		private String password;
		
		@Column(name="email",unique = true, nullable = false, updatable = false)
		private String email;
		private String phone;
		private boolean enabled;
		private String role;
		
		@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
		private ShoppingCart shoppingCart;
		
		
		public boolean isEnabled() {
			return enabled;
		}
		
		public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public ShoppingCart getShoppingCart() {
			return shoppingCart;
		}

		public void setShoppingCart(ShoppingCart shoppingCart) {
			this.shoppingCart = shoppingCart;
		}

}