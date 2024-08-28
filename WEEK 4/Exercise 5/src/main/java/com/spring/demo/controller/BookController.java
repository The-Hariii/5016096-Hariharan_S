package com.spring.demo.controller;


import com.spring.demo.model.Book;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private Long idCounter = 1L;

    // GET /books - Retrieve all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Books List");
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    // GET /books/{id} - Retrieve a specific book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = books.stream().filter(b -> b.getId().equals(id)).findFirst();
        if (book.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "Book Found");
            return new ResponseEntity<>(book.get(), headers, HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "Book Not Found");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    // POST /books - Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        book.setId(idCounter++);
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Book Created");
        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

    // PUT /books/{id} - Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = books.stream().filter(b -> b.getId().equals(id)).findFirst();

        if (existingBook.isPresent()) {
            books.remove(existingBook.get());
            updatedBook.setId(id);  // Ensure the ID remains the same
            books.add(updatedBook);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "Book Updated");
            return new ResponseEntity<>(updatedBook, headers, HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "Book Not Found");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /books/{id} - Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Optional<Book> existingBook = books.stream().filter(b -> b.getId().equals(id)).findFirst();

        if (existingBook.isPresent()) {
            books.remove(existingBook.get());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "Book Deleted");
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "Book Not Found");
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }
}

