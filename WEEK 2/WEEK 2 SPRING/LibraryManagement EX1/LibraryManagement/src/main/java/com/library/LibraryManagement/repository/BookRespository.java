package com.library.LibraryManagement.repository;

import com.library.LibraryManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRespository extends JpaRepository<Book,Long> {
}
