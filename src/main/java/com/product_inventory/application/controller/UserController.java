package com.product_inventory.application.controller;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product_inventory.application.dao.UserDao;
import com.product_inventory.application.entity.Users;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping
	@Produces(MediaType.APPLICATION_JSON)
	public Users getUserDetails(@QueryParam("username") String username, @QueryParam("authtoken") String authtoken) {
		try {
			return userDao.getUser(username, authtoken);
		} catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("emailId") String emailId,@RequestParam("password") String password) {
		Users user = new Users(firstName, lastName, emailId, password, true, "", "","USER_ROLE");
		return userDao.create(user);
	}
	
	@PostMapping("/authentication")
	public String loginUser(@RequestBody String body) {
		JSONObject data;
		try {
			data = new JSONObject(body);
			return userDao.verifyUser(data.get("username").toString(), data.get("password").toString());
		} catch (JSONException e) {
			return null;
		}
	}

}
