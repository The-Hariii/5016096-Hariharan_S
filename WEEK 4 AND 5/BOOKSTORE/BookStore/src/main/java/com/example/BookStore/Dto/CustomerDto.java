package com.example.BookStore.Dto;

import jakarta.validation.constraints.Email;
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
public class CustomerDto extends RepresentationModel<CustomerDto> {
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

    private Integer version;

}
