package com.example.airqualityproject.domain.model

data class Data(
    val aqi: String,
    val station: Station,
    val time: Time,
    val uid: Int
)