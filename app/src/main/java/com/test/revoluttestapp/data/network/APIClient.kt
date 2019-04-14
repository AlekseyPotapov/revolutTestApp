package com.test.revoluttestapp.data.network

import com.test.revoluttestapp.data.model.Response
import io.reactivex.Single
import retrofit2.http.GET

interface APIClient {

    @get:GET("latest?base=EUR")
    val getCurrencyList: Single<Response>
}