package com.androjavatech4u.services;

import java.util.List;
 import com.androjavatech4u.entities.Product;

public interface ProductService {

	public String saveProduct(Product book);
	public String saveMultipleProduct(List<Product> books);
	
	public List<Product> getAllProduct();
 

	
}
