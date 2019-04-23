package com.test.revoluttestapp.domain

import com.test.revoluttestapp.domain.mapper.toCurrencies
import com.test.revoluttestapp.domain.model.RateEntity
import com.test.revoluttestapp.presentation.model.Currency
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.lang.NullPointerException
import java.lang.NumberFormatException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ConverterInteractorImpl @Inject constructor(
    private val converterRepository: ConverterRepository
) : ConverterInteractor {

    private var isStopCurrenciesUpdating: Boolean = false
    private val subject: BehaviorSubject<List<Currency>> = BehaviorSubject.create() // TODO move to constructor
    //TODO RateEntity move to constructor
    private val firstRow by lazy {
        val name = "EUR"
        val rate = RateEntity.valueOf(name)

        Currency(
            name = name,
            longName = rate.value,
            icon = rate.icon,
            value = "100.0"
        )
    }

    init {
        startGettingCurrencies()
    }

    private fun startGettingCurrencies() =
        Observable
            .interval(0, ConverterInteractor.PERIOD, TimeUnit.MILLISECONDS)
            .takeWhile { !isStopCurrenciesUpdating }
            .filter { !isStopCurrenciesUpdating }
            .flatMapSingle { converterRepository.getCurrencyList() }
            .map {
                it
                    .toCurrencies()
                    .addTheFirstRow()
            }
            .subscribe({
                subject.onNext(it)
            }, {
                subject.onError(it)
            })

    override fun recalculate(value: String, standartCurrency: Currency) {
        Single.fromCallable {
            val currencies = subject.value
            currencies?.let {
                it.map { currency ->
                    currency.apply {
                        this.value = if (currency.name == standartCurrency.name) {
                            value
                        } else {
                            try {
                                value.toDouble() * currency.value.toDouble() / standartCurrency.value.toDouble()
                            } catch (e: NumberFormatException) {
                                value
                            }.toString()
                        }
                    }
                }
            } ?: throw NullPointerException()
        }
        .subscribeOn(Schedulers.io())
            .subscribe({
                subject.onNext(it)
            }, {
                subject.onError(it)
            })
    }

    override fun getCurrencyList(): Observable<List<Currency>> =
        subject

    override fun stopUpdating() {
        isStopCurrenciesUpdating = true
    }

    private fun List<Currency>.addTheFirstRow(): List<Currency> {
        return ArrayList(this).apply {
            add(0, firstRow)
        }
    }
}
