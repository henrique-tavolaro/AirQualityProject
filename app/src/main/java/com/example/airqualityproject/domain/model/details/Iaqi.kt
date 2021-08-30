package com.example.airqualityproject.domain.model.details


import com.google.gson.annotations.SerializedName

data class Iaqi(
    val co: Co,
    val dew: Dew,
    val h: H,
    val p: P,
    val pm10: Pm10X,
    val pm25: Pm25X,
    val t: T,
    val w: W,
    val wg: Wg
)