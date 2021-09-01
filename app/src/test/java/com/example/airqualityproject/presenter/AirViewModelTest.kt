package com.example.airqualityproject.presenter

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.airqualityproject.MainCoroutineRule
import com.example.airqualityproject.datasource.domain.FakeAirRepository
import com.example.airqualityproject.datasource.domain.response
import com.example.airqualityproject.domain.repositories.AirRepository
import com.example.airqualityproject.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class AirViewModelTest(
    private val token: String
) {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: AirViewModel

    private lateinit var repository: AirRepository


    val context = Mockito.mock(Context::class.java)

    @Before()
    fun setup(){
        repository = FakeAirRepository()
        viewModel = AirViewModel(repository, token)
    }


    @Test
    fun `get list of cities from repository`(){
        viewModel.search("honolulu")

        val result = viewModel.result.getOrAwaitValueTest()

        assert(result.getContentIfNotHandled() == response)


    }


}