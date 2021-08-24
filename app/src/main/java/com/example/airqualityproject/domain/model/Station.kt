package com.example.airqualityproject.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Station(
    val country: String,
    val geo: List<Double>,
    val name: String,
    val url: String
) : Parcelable