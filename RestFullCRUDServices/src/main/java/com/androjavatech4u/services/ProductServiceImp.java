package com.androjavatech4u.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.androjavatech4u.entities.Book;
import com.androjavatech4u.entities.Product;
import com.androjavatech4u.repository.ProductRapo;

@Service
public class ProductServiceImp implements ProductService
{

	@Autowired
	ProductRapo productRapo;
	
	
	
	@Override
	public String saveProduct(Product book) {
		Product product=productRapo.save(book);
		if(product!=null)
		return "success";
		else
			return "fail";
	}

	@Override
	public String saveMultipleProduct(List<Product> books) {
		 		return null;
	}

	@Override
	public List<Product> getAllProduct() {
	 
		return productRapo.findAll();
	}

}
