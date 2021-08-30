package com.example.airqualityproject.domain.model.details


import com.google.gson.annotations.SerializedName

data class TimeDetails(
    val iso: String,
    val s: String,
    val tz: String,
    val v: Int
)