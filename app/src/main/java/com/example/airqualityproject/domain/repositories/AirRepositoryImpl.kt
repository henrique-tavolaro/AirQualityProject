package com.example.airqualityproject.domain.repositories

import android.util.Log
import com.example.airqualityproject.datasource.RetrofitService
import com.example.airqualityproject.domain.model.Response

class AirRepositoryImpl(
    private val retrofitService: RetrofitService
) : AirRepository {
    override suspend fun search(
        city: String,
        token: String,

    ) : Response {
        Log.d("TAG11", city)
        return retrofitService.search(
            token,
            city
        )
    }
}