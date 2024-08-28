package com.example.demo.assembler;



import com.example.demo.controller.CustomerController;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerDTO;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAssembler {

    public CustomerDTO toModel(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());

        customerDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel());
        return customerDTO;
    }
}

