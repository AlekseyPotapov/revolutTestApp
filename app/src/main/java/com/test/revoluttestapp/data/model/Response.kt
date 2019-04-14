package com.test.revoluttestapp.data.model

data class Response(
    val base: String,
    val date: String,
    val rates: HashMap<String, Double>
)