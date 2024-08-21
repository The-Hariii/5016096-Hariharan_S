package com.example.BookStore.Entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Email
    @Size(min = 5, max = 100)
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    @Version
    private Integer version;
}
