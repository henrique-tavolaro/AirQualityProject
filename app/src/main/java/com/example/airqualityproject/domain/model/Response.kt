package com.example.airqualityproject.domain.model


import com.google.gson.annotations.SerializedName

data class Response(
    val data: List<Data>,
    val status: String
)