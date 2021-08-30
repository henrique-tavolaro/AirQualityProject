package com.example.airqualityproject.domain.model.details

import com.google.gson.annotations.SerializedName


data class ResponseDetails(
    val status: String,
    val data: Data,

)