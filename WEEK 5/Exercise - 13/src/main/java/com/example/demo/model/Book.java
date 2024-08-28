package com.example.demo.model;




import jakarta.validation.constraints.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Data // Lombok annotation for getters, setters, toString, etc.
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title can be up to 100 characters")
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(max = 50, message = "Author name can be up to 50 characters")
    private String author;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private Double price;

    @NotBlank(message = "ISBN is mandatory")
    @Size(max = 20, message = "ISBN can be up to 20 characters")
    private String isbn;
    @Version
    private Integer version; // For optimistic locking


    public Book(long l, String bookTitle, String authorName) {
    }
}
