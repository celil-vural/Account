package com.dev.account.dto;

import com.dev.account.model.Customer;
import org.jetbrains.annotations.NotNull;
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
        return new AccountCustomerDto(
                Objects.requireNonNull(from.getId()),
                Objects.requireNonNull(from.getName()),
                Objects.requireNonNull(from.getSurname()));
    }

    public CustomerDto convertToCustomerDto(@NotNull Customer from) {
        return new CustomerDto(
                Objects.requireNonNull(from.getId()),
                Objects.requireNonNull(from.getName()),
                Objects.requireNonNull(from.getSurname()),
                Objects.requireNonNull(from.getAccounts())
                        .stream()
                        .map(customerAccountDtoConverter::convert)
                        .collect(Collectors.toSet()));
    }
}
