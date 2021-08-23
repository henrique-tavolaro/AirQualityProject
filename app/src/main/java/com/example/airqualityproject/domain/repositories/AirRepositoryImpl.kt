package com.example.airqualityproject.domain.repositories

import com.example.airqualityproject.datasource.RetrofitService
import com.example.airqualityproject.domain.model.Response
import com.example.airqualityproject.utils.API_TOKEN

class AirRepositoryImpl(
    private val retrofitService: RetrofitService
) : AirRepository {
    override suspend fun search(
        token: String,
        city: String
    ) : Response {
        return retrofitService.search(
            token,
            city
        )
    }
}