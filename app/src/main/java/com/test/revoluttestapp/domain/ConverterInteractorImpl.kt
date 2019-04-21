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

    private val firstRow by lazy {
        val name = "EUR"
        val rate = RateEntity.valueOf(name)

        Currency(
            name = name,
            longName = rate.value,
            icon = rate.icon,
            value = "100"
        )
    }

    override fun getCurrencyList(): Observable<List<Currency>> =
        converterRepository.getCurrencyList()
            .map {
                it.toCurrency().addTheFirstRow()//.addTheFirstRow()
            }

    private fun List<Currency>.addTheFirstRow(): List<Currency> {
        return ArrayList(this).apply {
            add(0, firstRow)
        }
    }
}


