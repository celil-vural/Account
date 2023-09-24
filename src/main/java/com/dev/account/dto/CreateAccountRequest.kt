package com.dev.account.dto

import java.math.BigDecimal

data class CreateAccountRequest(
        val customerId:String,
        val initialCredit:BigDecimal,

){

}
