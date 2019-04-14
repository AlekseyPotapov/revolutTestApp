package com.test.revoluttestapp.domain

import com.test.revoluttestapp.presentation.model.Currency
import io.reactivex.Observable

interface ConverterInteractor {

    fun getCurrencyList(): Observable<List<Currency>>

}