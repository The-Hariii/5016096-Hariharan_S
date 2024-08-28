package com.example.demo.controller;



import com.example.demo.assembler.CustomerModelAssembler;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerDTO;
import com.example.demo.service.CustomerService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerModelAssembler customerModelAssembler;

    public CustomerController(CustomerService customerService, CustomerModelAssembler customerModelAssembler) {
        this.customerService = customerService;
        this.customerModelAssembler = customerModelAssembler;
    }

    @GetMapping("/{id}")
    public EntityModel<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        CustomerDTO customerDTO = customerModelAssembler.toModel(customer);
        return EntityModel.of(customerDTO);
    }

    @GetMapping
    public CollectionModel<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customerModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(customerDTOs);
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        return customerModelAssembler.toModel(savedCustomer);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return customerModelAssembler.toModel(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
