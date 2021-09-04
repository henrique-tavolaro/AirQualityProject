package com.example.airqualityproject.domain

import com.example.airqualityproject.datasource.MockWebServerResponse.detailsResponse
import com.example.airqualityproject.datasource.MockWebServerResponse.searchResponse
import com.example.airqualityproject.datasource.RetrofitService
import com.example.airqualityproject.domain.model.details.ResponseDetails
import com.example.airqualityproject.domain.model.search.Response
import com.example.airqualityproject.domain.repositories.AirRepository
import com.example.airqualityproject.domain.repositories.AirRepositoryImpl
import com.google.gson.GsonBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class AirRepositoryTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private val DUMMY_QUERY = "rome"
    private val DUMMY_TOKEN = "1234"

    //system in test
    private lateinit var repository: AirRepository

    // dependencies
    private lateinit var retrofitService: RetrofitService

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("search/")
        retrofitService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitService::class.java)

        repository = AirRepositoryImpl(retrofitService)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get list of cities from network and emit`() = runBlocking {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(searchResponse)
        )

        val flowItems = repository.search(DUMMY_QUERY, DUMMY_TOKEN).toList()

        //first emission should be loading
        assert(flowItems[0].loading)

        // second emission should be the response
        val response = flowItems[1].data

        assert(response != null)
        assert(response is Response)

        //loading should be false
        assert(!flowItems[1].loading)
    }


    @Test
    fun `try to get cities list and emit error`() = runBlocking {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .setBody("{}")
        )

        val flowItems = repository.search(DUMMY_QUERY, DUMMY_TOKEN).toList()

        //first emission should be loading
        assert(flowItems[0].loading)

        // second emission should be the error
        val response = flowItems[1].error

        assert(response != null)


        //loading should be false
        assert(!flowItems[1].loading)
    }
    @Test
    fun `get city details from network and emit`() = runBlocking {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(detailsResponse)
        )

        val flowItems = repository.getDetails(DUMMY_QUERY).toList()

        //first emission should be loading
        assert(flowItems[0].loading)

        // second emission should be the response
        val response = flowItems[1].data

        assert(response != null)
        assert(response is ResponseDetails)

        //loading should be false
        assert(!flowItems[1].loading)
    }


    @Test
    fun `try to get city details and emit error`() = runBlocking {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .setBody("{}")
        )

        val flowItems = repository.getDetails(DUMMY_QUERY).toList()

        //first emission should be loading
        assert(flowItems[0].loading)

        // second emission should be the error
        val response = flowItems[1].error

        assert(response != null)

        //loading should be false
        assert(!flowItems[1].loading)
    }
}






















