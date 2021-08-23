package com.example.airqualityproject.domain.repositories

import com.example.airqualityproject.domain.model.Response


interface AirRepository {

    suspend fun search(
        token: String,
        city: String
    ) : Response

}