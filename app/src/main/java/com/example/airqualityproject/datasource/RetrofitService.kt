package com.example.airqualityproject.datasource

import com.example.airqualityproject.domain.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/")
    suspend fun search(
        @Query("token") token: String,
        @Query("keyword") city: String,

        ) : Response

}