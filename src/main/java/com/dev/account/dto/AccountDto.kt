package com.dev.account.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountDto(
        val id: String?,
        val balance: BigDecimal?= BigDecimal.ZERO,
        val creationDate: LocalDateTime,
        val customer: AccountCustomerDto?,
        val transaction:Set<TransactionDto>?
)
