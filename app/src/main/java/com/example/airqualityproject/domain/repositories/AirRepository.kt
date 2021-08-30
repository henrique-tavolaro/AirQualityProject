package com.example.airqualityproject.domain.repositories

import com.example.airqualityproject.domain.model.details.ResponseDetails
import com.example.airqualityproject.domain.model.search.Response


interface AirRepository {

    suspend fun search(
        city: String,
        token: String,
    ) : Response

    suspend fun getDetails(
        city: String
    ) : ResponseDetails


}