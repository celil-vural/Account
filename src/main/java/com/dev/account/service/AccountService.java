package com.dev.account.service;

import com.dev.account.dto.AccountDto;
import com.dev.account.dto.AccountDtoConverter;
import com.dev.account.dto.CreateAccountRequest;
import com.dev.account.model.Account;
import com.dev.account.model.Customer;
import com.dev.account.model.Transaction;
import com.dev.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter accountDtoConverter;
    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountDtoConverter = accountDtoConverter;
    }
    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer= customerService.findCustomerById(createAccountRequest.getCustomerId());
        Account account= new Account(
                customer,
                createAccountRequest.getInitialCredit()
        );
        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO)>0){
            //Transaction transaction= transactionService.initialMoney(account,createAccountRequest.getInitialCredit());
            Transaction transaction=new Transaction(createAccountRequest.getInitialCredit(),account);
            account.getTransaction().add(transaction);
        }
        return accountDtoConverter.convert(accountRepository.save(account));
    }

}
