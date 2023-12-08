package com.example.circular.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;
  
  @Column(nullable = false)
  private String category;
  
  @Column(nullable = false)
  private int quantity;
  
  @Column(nullable = false)
  private double price;
  
  @Column(nullable = true)
  private Date expiryDate;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public Date getExpiryDate() {
	return expiryDate;
}

public void setExpiryDate(Date expiryDate) {
	this.expiryDate = expiryDate;
}

public Product(Long id, String name, String category, int quantity, double price, Date expiryDate) {
	super();
	this.id = id;
	this.name = name;
	this.category = category;
	this.quantity = quantity;
	this.price = price;
	this.expiryDate = expiryDate;
}

public Product() {
	super();
	// TODO Auto-generated constructor stub
}
  
  
}
	
	

