package com.lab4.my_lab4;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final List<Book> books = new ArrayList<>();
    private Long bookIdCounter = 1L; // Initialize counter to 1

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
    	return findBookById(id);
    }
    
    @PostMapping
    public Book addBook(@RequestBody Book book) {
	    book.setId(generateBookId());
	    books.add(book);
	    return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
	    Book existingBook = findBookById(id);
	    if (existingBook != null) {
		    existingBook.setTitle(updatedBook.getTitle());
		    existingBook.setAuthor(updatedBook.getAuthor());
		    existingBook.setPublicationYear(updatedBook.getPublicationYear());
	    }
	    return existingBook;
    }
    
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
	    Book bookToRemove = findBookById(id);
	    if (bookToRemove != null) {
	    	books.remove(bookToRemove);
	    }
    }

    private Book findBookById(Long id) {
        for(Book book:books) {
        	if(book.getId().equals(id)) {
        		return book;
        	}
        }
		return null;
    }

    private Long generateBookId() {
        return bookIdCounter++;
    }
}
