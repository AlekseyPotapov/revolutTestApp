package com.test.revoluttestapp.domain

import com.test.revoluttestapp.domain.model.CurrencyEntity
import io.reactivex.Single

interface ConverterRepository {

    fun getCurrencyList(): Single<List<CurrencyEntity>>

}