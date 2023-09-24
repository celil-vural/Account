package com.dev.account.dto;

import com.dev.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {
    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionDtoConverter transactionConverter;
    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter, TransactionDtoConverter transactionDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionConverter = transactionDtoConverter;
    }

    public AccountDto convert(Account from){
        return new AccountDto(
                from.getId(),from.getBalance(),
                from.getCreationDate(),
                customerDtoConverter.convertToAccountCustomer(from.getCustomer()),
                Objects.requireNonNull(from.getTransaction())
                        .stream()
                        .map(t->transactionConverter.convert(t))
                        .collect(Collectors.toSet()));
    }
}
