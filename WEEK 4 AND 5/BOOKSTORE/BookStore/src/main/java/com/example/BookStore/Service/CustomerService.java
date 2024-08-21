package com.example.BookStore.Service;

import com.example.BookStore.Dto.CustomerDto;
import com.example.BookStore.Entity.Customer;
import com.example.BookStore.Exception.ResourceNotFoundException;
import com.example.BookStore.Mapper.CustomerMapper;
import com.example.BookStore.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(savedCustomer);
    }

    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        return CustomerMapper.mapToCustomerDto(customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return CustomerMapper.mapToCustomerDtoList(customers);
    }

    @Transactional
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        Customer updatedCustomer = CustomerMapper.mapToCustomer(customerDto);
        updatedCustomer.setId(existingCustomer.getId());
        updatedCustomer.setVersion(existingCustomer.getVersion()); // Use version for optimistic locking
        Customer savedCustomer = customerRepository.save(updatedCustomer);
        return CustomerMapper.mapToCustomerDto(savedCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Customer not found with id " + id);
        }
    }
}
