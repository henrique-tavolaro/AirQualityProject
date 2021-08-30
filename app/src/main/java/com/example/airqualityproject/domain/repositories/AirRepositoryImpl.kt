package com.example.airqualityproject.domain.repositories

import android.util.Log
import com.example.airqualityproject.datasource.RetrofitService
import com.example.airqualityproject.domain.model.details.ResponseDetails
import com.example.airqualityproject.domain.model.search.Response

class AirRepositoryImpl(
    private val retrofitService: RetrofitService
) : AirRepository {
    override suspend fun search(
        city: String,
        token: String,
    ) : Response {
        return retrofitService.search(
            token,
            city
        )
    }

    override suspend fun getDetails(
        city: String): ResponseDetails {
        Log.d("TAG11", city)
        return retrofitService.getDetails(
            city
        )

    }
}