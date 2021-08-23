package com.example.airqualityproject.domain.model

data class Station(
    val country: String,
    val geo: List<Double>,
    val name: String,
    val url: String
)