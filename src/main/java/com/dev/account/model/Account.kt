package com.dev.account.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Account(
        @Id
        @GeneratedValue(generator ="UUID")
        @GenericGenerator(name ="UUID", strategy ="org.hibernate.id.UUIDGenerator")
        val id: String?,
        val ballance:BigDecimal?=BigDecimal.ZERO,
        val creationTimestamp: LocalDateTime,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "customer_id", nullable = false)
        val customer: Customer?,
        @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
        val transactions: Set<Transaction>?
){

}