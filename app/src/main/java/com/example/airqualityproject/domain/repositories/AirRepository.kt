package com.example.airqualityproject.domain.repositories

import com.example.airqualityproject.domain.model.Response


interface AirRepository {

    suspend fun search(
        city: String,
        token: String,

    ) : Response

}