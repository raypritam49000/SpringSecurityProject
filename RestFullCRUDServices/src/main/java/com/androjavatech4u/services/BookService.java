package com.androjavatech4u.services;
import java.util.List;
import java.util.Optional;

import com.androjavatech4u.entities.Book;

public interface BookService 
{
	
public String saveBook(Book book);
public String saveMultipleBook(List<Book> books);
public Optional<Book>  getBook(int id);
public  List<Book>  getBookByName(String bookName);
public List<Book> getAllBooks();
public String updateBook(Book book,int bookId);
public String deleteBook();
public  List<Book>  getBookByAuthour(String bookAuth);




}
