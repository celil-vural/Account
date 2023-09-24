package com.dev.account.service;

import com.dev.account.dto.CustomerDto;
import com.dev.account.dto.CustomerDtoConverter;
import com.dev.account.exception.CustomerNotFoundException;
import com.dev.account.model.Customer;
import com.dev.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;
    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }
    /**
     * @param id
     * @apiNote find customer by id
     * @implNote if customer could not find by id throw CustomerNotFoundException
     * @exception CustomerNotFoundException
     * @return Customer
     */
    protected Customer findCustomerById(String id){
        return customerRepository.findById(id).orElseThrow(
                ()-> new CustomerNotFoundException("Customer could not found by id: "+id));
    }

    public CustomerDto getCustomerById(String customerId) {

        return customerDtoConverter.convertToCustomerDto(findCustomerById(customerId));
    }
}
