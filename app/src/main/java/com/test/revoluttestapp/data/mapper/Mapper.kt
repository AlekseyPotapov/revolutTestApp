package com.test.revoluttestapp.data.mapper

import com.test.revoluttestapp.data.model.Response
import com.test.revoluttestapp.domain.model.CurrencyEntity

fun Response.toCurrencyEntityList(): List<CurrencyEntity> {
    return rates.map { entry ->
        CurrencyEntity(entry.key, entry.value)
    }
}