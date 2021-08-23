package com.example.airqualityproject.domain.model


import com.google.gson.annotations.SerializedName

data class Time(
    val stime: String,
    val tz: String,
    val vtime: Int
)