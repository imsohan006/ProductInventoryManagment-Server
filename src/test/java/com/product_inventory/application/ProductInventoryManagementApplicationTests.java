package com.product_inventory.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.apache.tomcat.util.security.MD5Encoder;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.product_inventory.application.dao.ProductDao;
import com.product_inventory.application.dao.UserDao;
import com.product_inventory.application.entity.Products;
import com.product_inventory.application.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInventoryManagementApplicationTests {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void contextLoads() {
	}
	
	/*
	 * User Dao Layer
	 * Test Cases
	*/
	
	/*@Test
	public void addUser() {
		Users user = new Users("Sam","Yad","sam@gmail.com","0119bbbbe6c770f3c8e0238510a3e720",true,"","","USER_ROLE");
		try {
			JSONObject response = new JSONObject(userDao.create(user));
			assertEquals("EmailId Already Exists", response.getString("message"));
			//assertEquals("User Created Succesfully", response.getString("message"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void verifyUser() {
		try {
			JSONObject response = new JSONObject(userDao.verifyUser("sam@gmail.com", "0119bbbbe6c770f3c8e0238510a3e720"));
			assertNotEquals(false, response.get("success"));
			if(response.get("success").equals(true)) {
				getUserDetails(response.get("authToken").toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void getUserDetails(String authtoken) {
		try {
			Users response = userDao.getUser("sam@gmail.com", authtoken);
			assertNotEquals(null, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/*
	 * Product Dao Layer
	 * Test Cases
	*/
	@Test
	public void addProduct() {
		Products product = new Products("Add Shirt","Clothes","Topwear",122,"2019-04-01","2019-04-21",500,"prod.png",true,10);
		try {
			String response = productDao.addProduct(product);
			assertEquals("Product Added Successfuly...", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void getProducts() {
		try {
			String products = "abc";
			assertNotEquals(null, products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getProductById() {
		try {
			Products product = productDao.findById(1);
			System.out.println(product);
			assertNotEquals(null, product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deactiveProduct() {
		String response;
		try {
			//response = productDao.deactivateById(1);
			//assertEquals("Updated...", response);
		} catch (Exception e) {
		}
	}*/
	
	

}
