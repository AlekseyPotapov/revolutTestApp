package com.test.revoluttestapp.domain

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

private fun List<CurrencyEntity>.toCurrency(): List<Currency> {
    return map { currencyEntity ->
        val rate = RateEntity.valueOf(currencyEntity.name)

        Currency(
            name = currencyEntity.name,
            longName = rate.value,
            icon = rate.icon,
            value = currencyEntity.value
        )
    }
}

