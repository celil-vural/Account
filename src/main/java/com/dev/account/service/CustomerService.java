package com.dev.account.service;

import com.dev.account.dto.CustomerDto;
import com.dev.account.dto.converter.CustomerDtoConverter;
import com.dev.account.exception.CustomerNotFoundException;
import com.dev.account.model.Customer;
import com.dev.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }
    /**
     * @param id
     * @apiNote find customer by id
     * @implNote if customer could not find by id throw CustomerNotFoundException
     * @exception CustomerNotFoundException
     * @return Customer
     */
    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not find by id: " + id));

    }

    public CustomerDto getCustomerById(String customerId) {
        return converter.convertToCustomerDto(findCustomerById(customerId));
    }

    public List<CustomerDto> getAllCustomer() {

        return customerRepository.findAll()
                .stream()
                .map(converter::convertToCustomerDto)
                .collect(Collectors.toList());
    }
}
