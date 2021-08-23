package com.example.airqualityproject.domain.model


import com.google.gson.annotations.SerializedName

data class Station(
    val country: String,
    val geo: List<Double>,
    val name: String,
    val url: String
)