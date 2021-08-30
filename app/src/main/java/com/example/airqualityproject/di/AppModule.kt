package com.example.airqualityproject.di

import com.example.airqualityproject.datasource.RetrofitService
import com.example.airqualityproject.domain.repositories.AirRepository
import com.example.airqualityproject.domain.repositories.AirRepositoryImpl
import com.example.airqualityproject.presenter.AirViewModel
import com.example.airqualityproject.utils.API_TOKEN
import com.example.airqualityproject.utils.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    val myModule = module{
        single { provideRetrofitService() }
        single { provideAuthToken() }
        single { provideRepository(get()) }
        viewModel { AirViewModel(get(), get()) }
    }

    val logger : HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    fun provideRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttp.build())
            .build()
            .create(RetrofitService::class.java)
    }

    fun provideAuthToken(): String{
        return API_TOKEN
    }

    fun provideRepository(
        retrofitService: RetrofitService
    ) : AirRepository {
        return AirRepositoryImpl(retrofitService)
    }

}
