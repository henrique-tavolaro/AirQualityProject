package com.example.airqualityproject.domain.repositories

import com.example.airqualityproject.datasource.RetrofitService
import com.example.airqualityproject.domain.model.DataState
import com.example.airqualityproject.domain.model.details.ResponseDetails
import com.example.airqualityproject.domain.model.search.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AirRepositoryImpl(
    private val retrofitService: RetrofitService
) : AirRepository {

    override suspend fun search(
        city: String,
        token: String,
    ) : Flow<DataState<Response?>> = flow {
        try {
            emit(DataState.loading())

            val result = retrofitService.search(
                token,
                city
            )

            emit(DataState.success(result))

        } catch (e: Exception){
            emit(DataState.error<Response>(e.message ?: "Unknown error"))
        }

    }

    override suspend fun getDetails(
        city: String): Flow<DataState<ResponseDetails?>> = flow {
        try {
            emit(DataState.loading())

            val result = retrofitService.getDetails(
                city
            )
            emit(DataState.success(result))

        } catch (e: Exception){
            emit(DataState.error<ResponseDetails>(e.message ?: "Unknown error"))
        }



    }
}