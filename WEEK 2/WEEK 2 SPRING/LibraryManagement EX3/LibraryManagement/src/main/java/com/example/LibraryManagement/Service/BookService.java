package com.example.LibraryManagement.Service;


import org.springframework.stereotype.Service;

@Service
public class BookService {

    public void addBook(String bookName) {
        // Simulate some processing
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
