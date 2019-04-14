package com.test.revoluttestapp.domain

import com.test.revoluttestapp.domain.model.CurrencyEntity
import io.reactivex.Observable

interface ConverterRepository {

    fun getCurrencyList(): Observable<List<CurrencyEntity>>

}