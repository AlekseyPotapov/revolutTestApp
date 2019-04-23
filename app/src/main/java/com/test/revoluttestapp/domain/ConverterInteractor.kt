package com.test.revoluttestapp.domain

import com.test.revoluttestapp.presentation.model.Currency
import io.reactivex.Observable

interface ConverterInteractor {

    fun getCurrencyList(): Observable<List<Currency>>

    fun recalculate(value: String, currency: Currency)

    fun stopUpdating()

    companion object {
        val PERIOD = 1000L
    }

}