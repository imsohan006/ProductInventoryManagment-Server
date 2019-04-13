/*
 * @Author : Sohan Lal Yadav
 */

package com.product_inventory.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String productName;
	
	private String productCategory;
	
	private String productSubCategory;
	
	private int quantity;
	
	private double price;
	
	private int discount;
	
	private String saleFrom;
	
	private String saleTo;
	
	private String image;
	
	private boolean isProductActive;

	public Products() {}

	public Products(String productName, String productCategory, String productSubCategory, int quantity, String saleFrom, String saleTo, double price, String image, boolean isProductActive, int discount) {
		super();
		this.productName = productName;
		this.productCategory = productCategory;
		this.productSubCategory = productSubCategory;
		this.quantity = quantity;
		this.setSaleFrom(saleFrom);
		this.setSaleTo(saleTo);
		this.price = price;
		this.image = image;
		this.isProductActive = isProductActive;
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public String getProductSubCategory() {
		return productSubCategory;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public void setProductSubCategory(String productSubCategory) {
		this.productSubCategory = productSubCategory;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSaleFrom() {
		return saleFrom;
	}

	public void setSaleFrom(String saleFrom) {
		this.saleFrom = saleFrom;
	}

	public String getSaleTo() {
		return saleTo;
	}

	public void setSaleTo(String saleTo) {
		this.saleTo = saleTo;
	}

	public boolean isProductActive() {
		return isProductActive;
	}

	public void setProductActive(boolean isProductActive) {
		this.isProductActive = isProductActive;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}
