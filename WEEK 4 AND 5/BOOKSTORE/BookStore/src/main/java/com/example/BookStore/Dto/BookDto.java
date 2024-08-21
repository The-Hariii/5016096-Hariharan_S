package com.example.BookStore.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends RepresentationModel<BookDto>  {
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @JsonProperty("book_title")
    private String title;

    @NotNull
    @Size(min = 1, max = 100)
    @JsonProperty("book_author")
    private String author;

    @Min(0)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "#.00")
    private double price;

    @NotNull
    @Size(min = 1, max = 13)
    private String isbn;

    private Integer version;


}
