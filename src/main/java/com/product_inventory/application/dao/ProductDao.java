package com.product_inventory.application.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.product_inventory.application.entity.Products;
import com.product_inventory.application.entity.Users;

@Service
@EnableTransactionManagement
public class ProductDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Products> getProduct() throws Exception {
		try {
			Query q = entityManager.createQuery("FROM Products");
			@SuppressWarnings("unchecked")
			List<Products> list = q.getResultList();
			return list;
		}catch (Exception e) {
			return null;
		}
	}
	
	@Transactional
	public String updateProduct(Products product) throws Exception{
		if(entityManager.merge(product) != null)
			return "Success";
		else
			return null;
	}
	

	public Products findById(int id){
		try {
			Query q = entityManager.createQuery("FROM Products WHERE id =:id");
			q.setParameter("id",id);
			return (Products) q.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public String deactivateById(int id, String authtoken) throws Exception{
		try {
			if(isSuperUser(authtoken)) {
				Query q = entityManager.createQuery("UPDATE Products SET isProductActive = false WHERE id =:id");
				q.setParameter("id", id);
				int result = q.executeUpdate();
				if(result==1) 
					return "Product Deactivated...";
				else
					return "Deactivation Failed...";
			}
		}catch (Exception e) {
			return "Deactivation Failed...";
		}
		return "Unauthorize User..";
	}
	
	@Transactional
	public String activateById(int id, String authtoken){
		try {
			if(isSuperUser(authtoken)) {
				Query q = entityManager.createQuery("UPDATE Products SET isProductActive = true WHERE id =:id");
				q.setParameter("id", id);
				int result = q.executeUpdate();
				if(result==1) 
					return "Product Activated...";
				else
					return "Activation Failed...";
			}
		}catch (Exception e) {
			return null;
		}
		return "Unauthorize User..";
	}

	@Transactional
	public String addProduct(Products product) {
		try {
			entityManager.persist(product);
			return "Product Added Successfuly...";
		}catch (Exception e) {
			return null;
		}
	}
	
	@Transactional
	private boolean isSuperUser(String authtoken) {
		try {
			Users u = (Users) entityManager.createQuery("FROM Users WHERE userAuthToken =:authtoken")
							.setParameter("authtoken",authtoken)
							.getSingleResult();
			if(u.getUserAuthToken().equals(authtoken))
				return true;
			else 
				return false;
		}catch (Exception e) {
			return false;
		}
	}
}
