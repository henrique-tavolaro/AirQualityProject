package com.example.airqualityproject.datasource

import com.example.airqualityproject.domain.model.details.ResponseDetails
import com.example.airqualityproject.domain.model.search.Response
import com.example.airqualityproject.utils.API_TOKEN
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/")
    suspend fun search(
        @Query("token") token: String,
        @Query("keyword") city: String,

        ) : Response

    @GET("feed/{city}/?token=${API_TOKEN}")
    suspend fun getDetails(
        @Path("city") city: String
    ) : ResponseDetails

}