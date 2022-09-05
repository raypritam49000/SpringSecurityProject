package com.androjavatech4u.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.androjavatech4u.entities.Book;

@Repository
public interface BookRapo extends JpaRepository<Book, Integer>
{
	public List<Book> findBybookName(String bookName);
	public List<Book> findBybookAuthour(String authName);
	
}
