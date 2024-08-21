package com.example.BookStore.Controller;

import com.example.BookStore.Dto.CustomerDto;
import com.example.BookStore.Service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<EntityModel<CustomerDto>> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomer = customerService.createCustomer(customerDto);
        EntityModel<CustomerDto> entityModel = EntityModel.of(savedCustomer);
        entityModel.add(WebMvcLinkBuilder.linkTo(CustomerController.class).slash(savedCustomer.getId()).withSelfRel());
        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDto>> getCustomerById(@PathVariable("id") Long customerId) {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        EntityModel<CustomerDto> entityModel = EntityModel.of(customerDto);
        entityModel.add(WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customerId).withSelfRel());
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CustomerDto>>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        List<EntityModel<CustomerDto>> customerEntities = customers.stream()
                .map(customerDto -> EntityModel.of(customerDto,
                        WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customerDto.getId()).withSelfRel()))
                .collect(Collectors.toList());
        CollectionModel<EntityModel<CustomerDto>> collectionModel = CollectionModel.of(customerEntities,
                WebMvcLinkBuilder.linkTo(CustomerController.class).withSelfRel());
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDto>> updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerDto updatedCustomer) {
        CustomerDto customerDto = customerService.updateCustomer(customerId, updatedCustomer);
        EntityModel<CustomerDto> entityModel = EntityModel.of(customerDto);
        entityModel.add(WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customerId).withSelfRel());
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }
}

