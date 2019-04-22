package com.test.revoluttestapp.data

import com.test.revoluttestapp.data.mapper.toCurrencyEntityList
import com.test.revoluttestapp.data.model.Response
import com.test.revoluttestapp.data.source.NetworkSource
import com.test.revoluttestapp.domain.ConverterRepository
import com.test.revoluttestapp.domain.model.CurrencyEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ConverterRepositoryImpl @Inject constructor(
    private val networkSource: NetworkSource
) : ConverterRepository {

    override fun getCurrencyList(): Observable<List<CurrencyEntity>> =
        Observable.interval(0, PERIOD, TimeUnit.MILLISECONDS)
            .flatMapSingle { networkSource.getCurrencyList() }
            .map { response ->
                response.toCurrencyEntityList()
            }
            .subscribeOn(Schedulers.io())

    companion object {
        val PERIOD = 1000L
    }
}

