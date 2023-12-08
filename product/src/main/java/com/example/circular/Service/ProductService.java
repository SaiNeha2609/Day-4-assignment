package com.example.circular.Service;

import java.util.List;

import com.example.circular.Model.Product;


public interface ProductService {
	public List<Product> getAllProducts();
	//public Product addProduct(Product product);
	public void deleteByID(long id);
	public Object save(Product product);

}
