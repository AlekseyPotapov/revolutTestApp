package com.test.revoluttestapp.domain

import com.test.revoluttestapp.domain.mapper.toCurrency
import com.test.revoluttestapp.domain.model.CurrencyEntity
import com.test.revoluttestapp.domain.model.RateEntity
import com.test.revoluttestapp.presentation.model.Currency
import io.reactivex.Observable
import javax.inject.Inject

class ConverterInteractorImpl @Inject constructor(
    private val converterRepository: ConverterRepository
) : ConverterInteractor {

    override fun getCurrencyList(): Observable<List<Currency>> =
        converterRepository.getCurrencyList()
            .map { it.toCurrency() }

}

