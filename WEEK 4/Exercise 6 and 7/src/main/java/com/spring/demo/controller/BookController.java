package com.spring.demo.controller;


import com.spring.demo.dto.BookDTO;
import com.spring.demo.mapper.BookMapper;
import com.spring.demo.model.Book;
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

    // GET /books - Retrieve all books
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return BookMapper.INSTANCE.bookToBookDTOList(books);
    }

    // GET /books/{id} - Retrieve a specific book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> book = books.stream().filter(b -> b.getId().equals(id)).findFirst();
        return book.map(value -> ResponseEntity.ok(BookMapper.INSTANCE.bookToBookDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /books - Add a new book
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
        books.add(book);
        return new ResponseEntity<>(BookMapper.INSTANCE.bookToBookDTO(book), HttpStatus.CREATED);
    }

    // PUT /books/{id} - Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Optional<Book> existingBook = books.stream().filter(b -> b.getId().equals(id)).findFirst();

        if (existingBook.isPresent()) {
            books.remove(existingBook.get());
            Book updatedBook = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
            updatedBook.setId(id);  // Ensure the ID remains the same
            books.add(updatedBook);
            return ResponseEntity.ok(BookMapper.INSTANCE.bookToBookDTO(updatedBook));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /books/{id} - Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Optional<Book> existingBook = books.stream().filter(b -> b.getId().equals(id)).findFirst();

        if (existingBook.isPresent()) {
            books.remove(existingBook.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


