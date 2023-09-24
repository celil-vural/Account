package com.dev.account.dto;

import com.dev.account.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {
    private final CustomerAccountDtoConverter customerAccountDtoConverter;

    public CustomerDtoConverter(CustomerAccountDtoConverter customerAccountDtoConverter) {
        this.customerAccountDtoConverter = customerAccountDtoConverter;
    }

    public AccountCustomerDto convertToAccountCustomer(Customer from){
        if(from==null) return new AccountCustomerDto("","","");
        return new AccountCustomerDto(from.getId(), from.getName(), from.getSurname());
    }

    public CustomerDto convertToCustomerDto(Customer from) {
        return new CustomerDto(
                from.getId(),
                from.getName(),
                from.getSurname(),
                Objects.requireNonNull(from.getAccounts())
                        .stream()
                        .map(customerAccountDtoConverter::convert)
                        .collect(Collectors.toSet()));
    }
}
