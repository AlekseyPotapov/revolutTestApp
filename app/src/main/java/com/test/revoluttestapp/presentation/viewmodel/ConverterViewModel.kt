package com.test.revoluttestapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.test.revoluttestapp.domain.ConverterInteractor
import com.test.revoluttestapp.presentation.model.Currency
import io.reactivex.BackpressureStrategy
import javax.inject.Inject

class ConverterViewModel @Inject constructor(
    private val converterInteractor: ConverterInteractor
): ViewModel() {

    val progressState: LiveData<List<Currency>> = LiveDataReactiveStreams.fromPublisher(
        converterInteractor.getCurrencyList().toFlowable(BackpressureStrategy.LATEST)
    )
}