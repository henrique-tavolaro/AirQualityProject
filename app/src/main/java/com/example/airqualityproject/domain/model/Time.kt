package com.example.airqualityproject.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Time(
    val stime: String,
    val tz: String,
    val vtime: Int
): Parcelable