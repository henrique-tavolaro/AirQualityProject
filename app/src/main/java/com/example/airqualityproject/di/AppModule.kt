package com.example.airqualityproject.di

import com.example.airqualityproject.datasource.RetrofitService
import com.example.airqualityproject.domain.repositories.AirRepository
import com.example.airqualityproject.domain.repositories.AirRepositoryImpl
import com.example.airqualityproject.presenter.AirViewModel
import com.example.airqualityproject.utils.API_TOKEN
import com.example.airqualityproject.utils.BASE_URL
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//@Module
//@InstallIn(SingletonComponent::class)
object AppModule {

    val myModule = module{
        single { provideRetrofitService() }
        single { provideAuthToken() }
        single { provideRepository(get()) }
        viewModel { AirViewModel(get(), get()) }
    }

//    @Singleton
//    @Provides
    fun provideRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitService::class.java)
    }

    /**
     * I might include proper authentication later on food2fork.ca
     * For now just hard code a token.
     */
//    @Singleton
//    @Provides
//    @Named("token")
    fun provideAuthToken(): String{
        return API_TOKEN
    }

//    @Singleton
//    @Provides
    fun provideRepository(
        retrofitService: RetrofitService
    ) : AirRepository {
        return AirRepositoryImpl(retrofitService)
    }



}