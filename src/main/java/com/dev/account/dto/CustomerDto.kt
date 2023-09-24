package com.dev.account.dto

data class CustomerDto(
        val id:String,
        val name:String,
        val surname:String,
        val account:Set<CustomerAccountDto>
)
