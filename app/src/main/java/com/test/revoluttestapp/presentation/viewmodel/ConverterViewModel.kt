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
import javax.inject.Inject

class ConverterViewModel @Inject constructor(
    private val converterInteractor: ConverterInteractor
): ViewModel() {

    private val LOG_TAG = "ConverterViewModel"

    private val compositeDisposable = CompositeDisposable()

    private var stopUpdating = false
    private var cachedCurrencyList: List<Currency>? = null

    val progressState: LiveData<List<Currency>> = LiveDataReactiveStreams.fromPublisher(
        converterInteractor
            .getCurrencyList()
            .filter { !stopUpdating }
            .doOnNext {
                cachedCurrencyList = it
                Log.d(LOG_TAG, "getCurrencyList() = $it")
            }
            .toFlowable(BackpressureStrategy.LATEST)
    )

    val calculation = MutableLiveData<List<Currency>>()

    fun selectItem(currency: Currency) {
        stopUpdating = true

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}