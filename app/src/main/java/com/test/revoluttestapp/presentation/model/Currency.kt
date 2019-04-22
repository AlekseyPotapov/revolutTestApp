package com.test.revoluttestapp.presentation.model

data class Currency(
    val name: String,
    val longName: String,
    val icon: String,
    val value: Double
) {
    var inFocus: Boolean = false
    var coefficient: Double = value / 100
}
