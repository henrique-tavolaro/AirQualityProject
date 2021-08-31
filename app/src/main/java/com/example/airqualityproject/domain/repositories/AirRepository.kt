package com.example.airqualityproject.domain.repositories

import com.example.airqualityproject.domain.model.DataState
import com.example.airqualityproject.domain.model.details.ResponseDetails
import com.example.airqualityproject.domain.model.search.Response
import kotlinx.coroutines.flow.Flow


interface AirRepository {

    suspend fun search(
        city: String,
        token: String,
    ) : Flow<DataState<Response?>>

    suspend fun getDetails(
        city: String
    ) : Flow<DataState<ResponseDetails?>>


}