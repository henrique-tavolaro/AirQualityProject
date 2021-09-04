package com.example.airqualityproject.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.airqualityproject.MainCoroutineRule
import com.example.airqualityproject.domain.FakeAirRepository
import com.example.airqualityproject.domain.detailsResponse
import com.example.airqualityproject.domain.response
import com.example.airqualityproject.domain.repositories.AirRepository
import com.example.airqualityproject.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AirViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: AirViewModel

    private lateinit var repository: AirRepository

    val DUMMY_TOKEN = "1234"

    @Before()
    fun setup(){
        repository = FakeAirRepository()
        viewModel = AirViewModel(repository, DUMMY_TOKEN)
    }


    @Test
    fun `get list of cities from repository`() {

        viewModel.search("honolulu")

        val result = viewModel.result.getOrAwaitValueTest()
        val message = viewModel.toastMessage.value

        assertThat(result.getContentIfNotHandled()?.data).isEqualTo(response.data)
        assertThat(message).isNull()
    }

    @Test
    fun `get error from repository when searching cities`() {

        viewModel.search("holulu")

        val result = viewModel.result.value
        val message = viewModel.toastMessage.getOrAwaitValueTest()

        assertThat(message.getContentIfNotHandled()).isEqualTo("Error fetching data")
        assertThat(result).isNull()
    }


    @Test
    fun `get city details from repository`(){

        viewModel.getDetails("honolulu")

        val result = viewModel.detailsResult.getOrAwaitValueTest()
        val message = viewModel.toastMessage.value

        assertThat(result.getContentIfNotHandled()?.data).isEqualTo(detailsResponse.data)
        assertThat(message).isNull()
    }


    @Test
    fun `get error from repository when getting details`() {

        viewModel.search("holulu")

        val result = viewModel.detailsResult.value
        val message = viewModel.toastMessage.getOrAwaitValueTest()

        assertThat(message.getContentIfNotHandled()).isEqualTo("Error fetching data")
        assertThat(result).isNull()
    }


}