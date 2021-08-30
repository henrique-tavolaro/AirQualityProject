package com.example.airqualityproject.domain.model.details


import com.google.gson.annotations.SerializedName

data class City(
    val geo: List<Double>,
    val name: String,
    val url: String
)