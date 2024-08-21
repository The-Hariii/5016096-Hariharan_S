package com.example.BookStore.Controller;

import com.example.BookStore.Dto.BookDto;
import com.example.BookStore.Service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
@Api(value = "Book Management System", description = "Operations pertaining to books in Book Management System")
public class BookController {

    private final BookService bookService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Create a new book", response = EntityModel.class)
    public ResponseEntity<EntityModel<BookDto>> createBook(
            @ApiParam(value = "Book details to be created", required = true) @RequestBody BookDto bookDto) {
        BookDto savedBook = bookService.createBook(bookDto);
        EntityModel<BookDto> entityModel = EntityModel.of(savedBook);
        entityModel.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(savedBook.getId()).withSelfRel());
        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Get a book by ID", response = EntityModel.class)
    public ResponseEntity<EntityModel<BookDto>> getBookById(
            @ApiParam(value = "ID of the book to be retrieved", required = true) @PathVariable("id") Long bookId) {
        BookDto bookDto = bookService.getBookById(bookId);
        EntityModel<BookDto> entityModel = EntityModel.of(bookDto);
        entityModel.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(bookId).withSelfRel());
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Get a list of all books", response = CollectionModel.class)
    public ResponseEntity<CollectionModel<EntityModel<BookDto>>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        List<EntityModel<BookDto>> bookEntities = books.stream()
                .map(bookDto -> EntityModel.of(bookDto,
                        WebMvcLinkBuilder.linkTo(BookController.class).slash(bookDto.getId()).withSelfRel()))
                .collect(Collectors.toList());
        CollectionModel<EntityModel<BookDto>> collectionModel = CollectionModel.of(bookEntities,
                WebMvcLinkBuilder.linkTo(BookController.class).withSelfRel());
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Update an existing book", response = EntityModel.class)
    public ResponseEntity<EntityModel<BookDto>> updateBook(
            @ApiParam(value = "ID of the book to be updated", required = true) @PathVariable("id") Long bookId,
            @ApiParam(value = "Updated book details", required = true) @RequestBody BookDto updatedBook) {
        BookDto bookDto = bookService.updateBook(bookId, updatedBook);
        EntityModel<BookDto> entityModel = EntityModel.of(bookDto);
        entityModel.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(bookId).withSelfRel());
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a book by ID")
    public ResponseEntity<String> deleteBook(
            @ApiParam(value = "ID of the book to be deleted", required = true) @PathVariable("id") Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search for books by title and/or author", response = CollectionModel.class)
    public ResponseEntity<CollectionModel<EntityModel<BookDto>>> searchBooks(
            @ApiParam(value = "Title of the book to search for") @RequestParam(required = false) String title,
            @ApiParam(value = "Author of the book to search for") @RequestParam(required = false) String author) {
        List<BookDto> books = bookService.searchBooks(title, author);
        List<EntityModel<BookDto>> bookEntities = books.stream()
                .map(bookDto -> EntityModel.of(bookDto,
                        WebMvcLinkBuilder.linkTo(BookController.class).slash(bookDto.getId()).withSelfRel()))
                .collect(Collectors.toList());
        CollectionModel<EntityModel<BookDto>> collectionModel = CollectionModel.of(bookEntities,
                WebMvcLinkBuilder.linkTo(BookController.class).withSelfRel());
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }
}
