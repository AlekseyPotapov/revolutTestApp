package com.test.revoluttestapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.revoluttestapp.domain.ConverterInteractor
import com.test.revoluttestapp.presentation.model.Currency
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import java.lang.NumberFormatException
import javax.inject.Inject

class ConverterViewModel @Inject constructor(
    private val converterInteractor: ConverterInteractor
): ViewModel() {

    private val LOG_TAG = "ConverterViewModel"

    private val compositeDisposable = CompositeDisposable()

    val progressState: LiveData<List<Currency>> = LiveDataReactiveStreams.fromPublisher(
        converterInteractor
            .getCurrencyList()
            .doOnNext {
                Log.d(LOG_TAG, "getCurrencyList() = $it")
            }
            .toFlowable(BackpressureStrategy.LATEST)
    )

    val calculation = MutableLiveData<List<Currency>>()

    fun selectItem() {
        converterInteractor.stopUpdating()
    }

    fun calculateNewCurrencies(value: String, currency: Currency) {
        Log.d(LOG_TAG, "string = $value currency = $currency")
        converterInteractor.recalculate(value, currency)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}