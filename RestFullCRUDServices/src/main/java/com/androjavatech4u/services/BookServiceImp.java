package com.androjavatech4u.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.androjavatech4u.entities.Book;
import com.androjavatech4u.repository.BookRapo;

@Service
public class BookServiceImp implements BookService
{
	
	@Autowired
	BookRapo bookRpo;

	@Override
	public String saveBook(Book book) { 
		Book book2=bookRpo.save(book);
		if(book2!=null)
		return "book saved successfllfy";
		else
			return "book Not saved successfllfy";
				
	}

	@Override
	public String saveMultipleBook(List<Book> books) {
		bookRpo.saveAll(books);	
		return "mutile book saveddd...";
	}

	
	
	@Override
	public Optional<Book> getBook(int id) {
		return bookRpo.findById(id);
	}

	@Override
	public List<Book> getAllBooks() {
		 
		return bookRpo.findAll();
	}

	@Override
	public String updateBook(Book book,int bookId) 
	{

		Optional<Book> b=bookRpo.findById(bookId);
		System.out.println("-------------->"+b);
		
		if(!b.isEmpty())
		{
		book.setId(bookId);	
		bookRpo.save(book);
		return "book save succesfuyy";
		}
		else
		{
		return "book  does not exist";	
		}
		}

	@Override
	public String deleteBook() {
		return null;
	}

	@Override
	public  List<Book> getBookByName(String bookName) {
		return bookRpo.findBybookName(bookName);
		
	}

	@Override
	public List<Book> getBookByAuthour(String bookAuth) {
		return bookRpo.findBybookAuthour(bookAuth);
	}
	
	
 
}
