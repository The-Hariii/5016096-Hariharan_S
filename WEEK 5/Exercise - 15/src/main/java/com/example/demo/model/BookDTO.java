package com.example.demo.model;


import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
@Data
public class BookDTO extends RepresentationModel<BookDTO> {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Double price;

    // Getters and Setters


}
