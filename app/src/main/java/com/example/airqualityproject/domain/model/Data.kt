package com.example.airqualityproject.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val aqi: String,
    val station: Station,
    val time: Time,
    val uid: Int
) : Parcelable