package com.lab3.my_lab3;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final List<Book> books = new ArrayList<Book>();
	
	@GetMapping
	public List<Book> getAllBooks() {
		return books;
	}
	
	@PostMapping
	public Book addBook(@RequestBody Book book) {
		books.add(book);
		return book;
	}
}