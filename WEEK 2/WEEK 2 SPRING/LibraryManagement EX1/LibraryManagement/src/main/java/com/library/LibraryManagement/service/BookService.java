package com.library.LibraryManagement.service;

import com.library.LibraryManagement.model.Book;
import com.library.LibraryManagement.repository.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
   private final BookRespository bookRespository;
    @Autowired
    public BookService(BookRespository bookRespository) {
        this.bookRespository = bookRespository;
    }



    public Book save(Book book) {
        return bookRespository.save(book);
    }

    public List<Book> findAll() {
        return bookRespository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRespository.findById(id);
    }

    public void delete(Long id) {
        bookRespository.deleteById(id);
    }
}
