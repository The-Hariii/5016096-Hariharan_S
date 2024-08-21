package com.example.BookStore.Mapper;

import com.example.BookStore.Controller.BookController;
import com.example.BookStore.Dto.BookDto;
import com.example.BookStore.Entity.Book;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookDto mapToBookDto(Book book) {
        BookDto bookDto = new BookDto(
                book.getId(),
                book.getVersion(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getIsbn()
        );

        // Add self link
        bookDto.add(WebMvcLinkBuilder.linkTo(BookController.class).slash(book.getId()).withSelfRel());

        return bookDto;
    }

    public static List<BookDto> mapToBookDtoList(List<Book> books) {
        return books.stream()
                .map(BookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    public static Book mapToBook(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getVersion(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPrice(),
                bookDto.getIsbn()
        );
    }
}
