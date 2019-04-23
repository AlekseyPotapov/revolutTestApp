package com.test.revoluttestapp.domain.mapper

import com.test.revoluttestapp.domain.model.CurrencyEntity
import com.test.revoluttestapp.domain.model.RateEntity
import com.test.revoluttestapp.presentation.model.Currency

fun List<CurrencyEntity>.toCurrencies(): List<Currency> {
    return map { currencyEntity ->
        val rate = RateEntity.valueOf(currencyEntity.name)

        Currency(
            name = currencyEntity.name,
            longName = rate.value,
            icon = rate.icon,
            value = currencyEntity.value.toString()
        )
    }
}