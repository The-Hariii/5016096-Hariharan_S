package com.example.BookStore.Mapper;

import com.example.BookStore.Controller.CustomerController;
import com.example.BookStore.Dto.CustomerDto;
import com.example.BookStore.Entity.Customer;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto(
                customer.getId(),
                customer.getVersion(),
                customer.getName(),
                customer.getEmail(),
                customer.getPassword()
        );

        // Add self link
        customerDto.add(WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customer.getId()).withSelfRel());

        return customerDto;
    }

    public static List<CustomerDto> mapToCustomerDtoList(List<Customer> customers) {
        return customers.stream()
                .map(CustomerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    public static Customer mapToCustomer(CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getVersion(),
                customerDto.getName(),
                customerDto.getEmail(),
                customerDto.getPassword()
        );
    }
}
