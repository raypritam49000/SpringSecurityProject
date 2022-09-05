package com.androjavatech4u.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.androjavatech4u.entities.Book;
import com.androjavatech4u.services.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/saveBook")
	public String saveBook(@RequestBody Book book) {
		String msg = bookService.saveBook(book);
		return msg;
	}

	@PostMapping("/saveMultipleBook")
	public String saveMultipleBook(@RequestBody List<Book> books) {
		bookService.saveMultipleBook(books);
		return "book saved.....";
	}

	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return books;
	}

	@GetMapping("/getBookById/{bookId}")
	public Optional<Book> getBookById(@PathVariable("bookId") int id) {
		Optional<Book> books = bookService.getBook(id);
		return books;
	}

	@GetMapping("/getBookByName/{bookName}")
	public List<Book> getBookByName(@PathVariable("bookName") String bookName) {
		List<Book> books = bookService.getBookByName(bookName);
		return books;
	}

	@GetMapping("/getBookByAuth/{bookAuth}")
	public List<Book> getBookByAuthour(@PathVariable("bookAuth") String bookAuth) {
		List<Book> books = bookService.getBookByAuthour(bookAuth);
		return books;
	}

	@PutMapping("/updatebook/{bookid}")
	public String updatebook(@RequestBody Book book, @PathVariable("bookid") int id) {
		return bookService.updateBook(book, id);
	}

}
