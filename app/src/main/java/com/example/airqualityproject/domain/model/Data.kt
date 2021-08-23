package com.example.airqualityproject.domain.model


import com.google.gson.annotations.SerializedName

data class Data(
    val aqi: String,
    val station: Station,
    val time: Time,
    val uid: Int
)