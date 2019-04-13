package com.product_inventory.application.dao;

import java.security.SecureRandom;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.product_inventory.application.customexception.CustomeException;
import com.product_inventory.application.entity.Users;

@Service
@Transactional
public class UserDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	 /**
	   * Save the user in the database.
	   */
	public String create(Users user) {
		 try{
			Users existingUser = (Users) entityManager.createQuery(
				        "FROM Users WHERE emailId = :username and password = :password")
				        .setParameter("username", user.getEmailId())
				        .setParameter("password", user.getPassword())
				        .getSingleResult();
		    if(existingUser.getEmailId() != null){
				 return "{\"success\": false,\"message\":\"EmailId Already Exists\"}";
			}else {
				 user.setEmailActivationLink(createRandomCode(30, "sadsf128DFcfs652GDv9879DVdvcb213lfdDFSDA739NFdsertDSFEUOB"));
				 entityManager.persist(user);
				 return "{\"success\": true,\"message\":\"User Created Succesfully\"}";
			}
		 }catch (NoResultException nre) {
			user.setEmailActivationLink(createRandomCode(30, "sadsf128DFcfs652GDv9879DVdvcb213lfdDFSDA739NFdsertDSFEUOB"));
			entityManager.persist(user);
			return "{\"success\": true,\"message\":\"User Created Succesfully\"}";
		 }catch(Exception e){
			return "{\"success\": false,\"message\":\""+e.getMessage()+"\"}";
		 }
	  }
	
	/**
	   * Return the user for login.
	   */
	  public String verifyUser(String username, String password) {
		  try{
			    Users user = (Users) entityManager.createQuery(
			        "from Users where emailId = :username and password = :password")
			        .setParameter("username", username)
			        .setParameter("password", password)
			        .getSingleResult();
			    if(user.isUserActive()){
			    	String authtoken = createRandomCode(20, "ABCD23EFGHIJKLqwerty45uiopasdf980ghjklzxxcvb21n23mMNOP45QRSTU56VWXYZ");
			    	if(user.getHasRole().equalsIgnoreCase("ADMIN_ROLE"))
			    		authtoken = authtoken+"@@Ad";
			    	user.setUserAuthToken(authtoken);
			    	entityManager.merge(user);
					return "{\"success\": true,\"authToken\":\""+authtoken+"\",\"username\":\""+user.getFirstName()+"\"}";
			    } else if(!user.isUserActive()){
			    	return "{\"success\": false,\"message\":\"Account not active\"}"; 
			    }else {
					return "{\"success\": false,\"message\":\"Username Or Password Wrong\"}";
				}
		  	}catch (NoResultException e) {
		  		return "{\"success\": false,\"message\":\"Username Or Password Wrong\"}";
			}catch(Exception e){
				throw new CustomeException();
		  	}
	  }
	  
	  public static String createRandomCode(int codeLength, String id) {
		    return new SecureRandom()
		            .ints(codeLength, 0, id.length())
		            .mapToObj(id::charAt)
		            .map(Object::toString)
		            .collect(Collectors.joining());
		}

	public Users getUser(String username, String authtoken) {
		
		try {
			Users user = (Users) entityManager.createQuery(
			        "from Users where emailId = :username and userAuthToken = :authToken")
			        .setParameter("username", username)
			        .setParameter("authToken", authtoken)
			        .getSingleResult();
			return user;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
}
