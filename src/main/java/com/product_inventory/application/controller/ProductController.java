package com.product_inventory.application.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product_inventory.application.dao.ProductDao;
import com.product_inventory.application.entity.Products;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	@GetMapping
	@Produces(MediaType.APPLICATION_JSON)
	public List<Products> getAllProduct() throws Exception {
		return productDao.getProduct();
	}
	
	@GetMapping("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Products getProductById(@PathVariable int id) throws Exception {
		return productDao.findById(Integer.valueOf(id));
	}
	
	@PostMapping
	public String addProduct(@RequestBody String reqdata) {
		
		try {
			JSONObject data = new JSONObject(reqdata);
			Products product = new Products(data.getString("productName"), data.getString("productCategory"), data.getString("productSubCategory"),
					data.getInt("quantity"), data.getString("saleFrom"), data.getString("saleTo"), data.getDouble("price"), data.getString("image"), true, data.getInt("discount"));
			return productDao.addProduct(product);
		} catch (JSONException e) {
			return null;
		}
	}
	
	@PutMapping("/activeproduct")
	public String activeProductById(@RequestBody String reqdata) {
		try {
			JSONObject paramdata = new JSONObject(reqdata).getJSONObject("params");
			int id = paramdata.getInt("id");
			String authToken = paramdata.getString("authtoken");
			return productDao.activateById(id, authToken);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@PutMapping("/{id}")
	public String updateProduct(@PathVariable int id, @RequestBody String reqData) throws Exception {
		JSONObject data;
		try {
			data = new JSONObject(reqData);
			String productName = data.getString("productName");
			String productCategory = data.getString("productCategory");
			String productSubCategory = data.getString("productSubCategory");
			int quantity = (int) data.get("quantity");
			int discount = (int) data.get("discount");
			String saleFrom = data.getString("saleFrom");
			String saleTo = data.getString("saleTo");
			double price = data.getDouble("price");
			String image = data.getString("image");
			Products product = new Products(productName, productCategory, productSubCategory, quantity, saleFrom, saleTo, price, image, true, discount);
			product.setId(id);
			return productDao.updateProduct(product);
		}catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public String desiableProductById(@PathVariable int id, @RequestHeader(value="authToken") String authToken) throws Exception {
		return productDao.deactivateById(id, authToken);
	}
	
	/*
	 * JSONObject data;
		try {
			data = new JSONObject(body);
			String productName = data.getString("productName");
			String productCategory = data.getString("productCategory");
			String productSubCategory = data.getString("productSubCategory");
			int quantity = (int) data.get("quantity");
			Date saleFrom = (Date) data.get("saleFrom");
			Date saleTo = (Date) data.get("saleTo");
			double price = (double) data.get("price");
			String image = data.getString("image");
			Products product = new Products(productName, productCategory, productSubCategory, quantity, saleFrom, saleTo, price, image);
			product.setId(id);
			return productDao.updateProduct(product);
		}catch (Exception e) {
			// TODO: handle exception
			return new CustomeException().toString();
		}
	 * */
}
