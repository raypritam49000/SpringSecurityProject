package com.androjavatech4u.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.androjavatech4u.entities.Product;

@Repository
public interface ProductRapo extends JpaRepository<Product, Integer>
{

}
