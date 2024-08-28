package com.example.demo.controller;




import com.example.demo.assembler.BookModelAssembler;
import com.example.demo.model.Book;
import com.example.demo.model.BookDTO;
import com.example.demo.service.BookService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookModelAssembler bookModelAssembler;

    public BookController(BookService bookService, BookModelAssembler bookModelAssembler) {
        this.bookService = bookService;
        this.bookModelAssembler = bookModelAssembler;
    }

    @GetMapping("/{id}")
    public EntityModel<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        BookDTO bookDTO = bookModelAssembler.toModel(book);
        return EntityModel.of(bookDTO);
    }

    @GetMapping
    public CollectionModel<BookDTO> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDTO> bookDTOs = books.stream()
                .map(bookModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(bookDTOs);
    }

    @PostMapping
    public BookDTO createBook(@RequestBody Book book) {
        Book savedBook = bookService.createBook(book);
        return bookModelAssembler.toModel(savedBook);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return bookModelAssembler.toModel(updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
