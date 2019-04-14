package com.test.revoluttestapp.data.source

import com.test.revoluttestapp.data.model.Response
import com.test.revoluttestapp.data.network.APIClient
import io.reactivex.Single
import javax.inject.Inject

class NetworkSource @Inject constructor(
    private val apiClient: APIClient
) {

    fun getCurrencyList(): Single<Response> =
        apiClient.getCurrencyList


}