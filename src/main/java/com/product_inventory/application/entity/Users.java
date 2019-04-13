package com.product_inventory.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private String password;

	private boolean isUserActive;
	
	private String userAuthToken;
	
	private String emailActivationLink;
	
	private String hasRole;

	public Users() {
		super();
	}
	
	public Users(String firstName, String lastName, String emailId, String password, boolean isUserActive,
			String userAuthToken, String emailActivationLink, String hasRole) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.isUserActive = isUserActive;
		this.userAuthToken = userAuthToken;
		this.emailActivationLink = emailActivationLink;
		this.hasRole = hasRole;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPassword() {
		return password;
	}

	public boolean isUserActive() {
		return isUserActive;
	}

	public String getUserAuthToken() {
		return userAuthToken;
	}

	public String getEmailActivationLink() {
		return emailActivationLink;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserActive(boolean isUserActive) {
		this.isUserActive = isUserActive;
	}

	public void setUserAuthToken(String userAuthToken) {
		this.userAuthToken = userAuthToken;
	}

	public void setEmailActivationLink(String emailActivationLink) {
		this.emailActivationLink = emailActivationLink;
	}

	public String getHasRole() {
		return hasRole;
	}

	public void setHasRole(String hasRole) {
		this.hasRole = hasRole;
	}
	
	/*private Users(UserBuilder builder) {
	        this.firstName = builder.firstName;
	        this.lastName = builder.lastName;
	        this.emailId = builder.emailId;
	        this.password = builder.password;
	        this.isUserActive = builder.isUserActive;
	        this.userAuthToken = builder.userAuthToken;
	        this.emailActivationLink = builder.emailActivationLink;
	    }
	 
	    public static class UserBuilder
	    {
	        private final String firstName;
	        private final String lastName;
	        private String emailId;
	        private String password;
	        private boolean isUserActive;
	        private String userAuthToken;
	        private String emailActivationLink;
	 
	        public UserBuilder(String firstName, String lastName) {
	            this.firstName = firstName;
	            this.lastName = lastName;
	        }
	        
	        public UserBuilder emailId(String emailId) {
	            this.emailId = emailId;
	            return this;
	        }
	        
	        public UserBuilder password(String password) {
	            this.password = password;
	            return this;
	        }
	        
	        public UserBuilder isUserActive(boolean isUserActive) {
	            this.isUserActive = isUserActive;
	            return this;
	        }
	        
	        public UserBuilder userAuthToken(String userAuthToken) {
	            this.userAuthToken = userAuthToken;
	            return this;
	        }
	        
	        public UserBuilder emailActivationLink(String emailActivationLink) {
	            this.emailActivationLink = emailActivationLink;
	            return this;
	        }
	        
	        //Return the finally consrcuted User object
	        public Users build() {
	            Users user =  new Users(this);
	            //validateUserObject(user);
	            return user;
	        }
	        
	        private void validateUserObject(Users user) {
	            //Do some basic validations to check
	            //if user object does not break any assumption of system
	        }
	        
	    }*/
}
