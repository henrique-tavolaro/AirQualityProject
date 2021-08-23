package com.example.airqualityproject.domain.model

data class Response(
    val `data`: List<Data>,
    val status: String
)