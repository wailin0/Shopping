//entity class for user which include login and user info 

package com.wlh.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
	

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="id", nullable = false, updatable = false)
		private Long id;
		private String username;
		private String password;
		
		@Column(name="email", nullable = false, updatable = false)
		private String email;
		private String phone;
		private String address;
		private boolean enabled;

		
		
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}


		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		

}