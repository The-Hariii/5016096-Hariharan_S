package com.example.BookStore.Service;


import com.example.BookStore.Dto.BookDto;
import com.example.BookStore.Entity.Book;
import com.example.BookStore.Mapper.BookMapper;
import com.example.BookStore.Metrics.BookStoreMetrics;
import com.example.BookStore.Repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookStoreMetrics bookStoreMetrics;


    public BookDto createBook(BookDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        Book savedBook = bookRepository.save(book);
        bookStoreMetrics.incrementBookCreatedCount();
        return BookMapper.mapToBookDto(savedBook);
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
        bookStoreMetrics.incrementBookReadCount();
        return BookMapper.mapToBookDto(book);
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        bookStoreMetrics.incrementBookReadCount();
        return books.stream()
                .map(BookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setIsbn(bookDto.getIsbn());
        Book updatedBook = bookRepository.save(book);
        bookStoreMetrics.incrementBookUpdatedCount();
        return BookMapper.mapToBookDto(updatedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id " + id);
        }
        bookRepository.deleteById(id);
        bookStoreMetrics.incrementBookDeletedCount();
    }

    public List<BookDto> searchBooks(String title, String author) {
        List<Book> books;
        if (title != null && author != null) {
            books = bookRepository.findByTitleContainingAndAuthorContaining(title, author);
        } else if (title != null) {
            books = bookRepository.findByTitleContaining(title);
        } else if (author != null) {
            books = bookRepository.findByAuthorContaining(author);
        } else {
            books = bookRepository.findAll();
        }
        bookStoreMetrics.incrementBookReadCount();
        return books.stream()
                .map(BookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }
}
