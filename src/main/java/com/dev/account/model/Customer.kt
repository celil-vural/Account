package com.dev.account.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.GenericGenerator

@Entity
data class Customer(
        @Id
        @GeneratedValue(generator ="UUID")
        @GenericGenerator(name ="UUID", strategy ="org.hibernate.id.UUIDGenerator")
        val id: String?,
        val name: String?,
        val surname: String?,

        @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
        val accounts: Set<Account>?
)
