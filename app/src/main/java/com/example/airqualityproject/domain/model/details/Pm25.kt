package com.example.airqualityproject.domain.model.details


import com.google.gson.annotations.SerializedName

data class Pm25(
    val avg: Int,
    val day: String,
    val max: Int,
    val min: Int
)