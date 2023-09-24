package com.dev.account.service;

import com.dev.account.dto.CustomerDto;
import com.dev.account.dto.CustomerDtoConverter;
import com.dev.account.exception.CustomerNotFoundException;
import com.dev.account.model.Customer;
import com.dev.account.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Set;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
public class CustomerServiceTest {
    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;

    @BeforeEach
    public void setUp() {
        customerRepository= mock(CustomerRepository.class);
        customerDtoConverter=mock(CustomerDtoConverter.class);
        customerService = new CustomerService(customerRepository, customerDtoConverter);
    }
    @Test
    public void testFindCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("id", "name", "surname", Set.of());
        Mockito.when(customerRepository.findById("id")).thenReturn(java.util.Optional.of(customer));
        Customer result = customerService.findCustomerById("id");
        Assertions.assertEquals(customer, result);
    }
    @Test
    public void testFindCustomerById_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("id")).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(
                CustomerNotFoundException.class,
                ()->customerService.findCustomerById("id")
        );
    }
    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("id", "name", "surname", Set.of());
        CustomerDto customerDto = new CustomerDto("id", "name", "surname",Set.of());
        Mockito.when(customerRepository.findById("id")).thenReturn(java.util.Optional.of(customer));
        Mockito.when(customerDtoConverter.convertToCustomerDto(customer)).thenReturn(customerDto);
        CustomerDto result = customerService.getCustomerById("id");
        Assertions.assertEquals(customerDto, result);
    }
    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("id")).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(
                CustomerNotFoundException.class,
                ()->customerService.getCustomerById("id")
        );
        Mockito.verifyNoInteractions(customerDtoConverter);
    }
}
