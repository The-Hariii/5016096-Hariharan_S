package com.example.BookStore.Entity;

import jakarta.persistence.*;
import jakarta.persistence.ValidationMode.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Size(min = 1, max = 100)
    private String author;

    @Min(0)
    private double price;

    @NotNull
    @Size(min = 1, max = 13)
    private String isbn;


    @Version
    private Integer version;
}