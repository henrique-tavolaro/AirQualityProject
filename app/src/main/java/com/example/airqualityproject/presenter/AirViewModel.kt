package com.example.airqualityproject.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airqualityproject.domain.repositories.AirRepository
import com.example.airqualityproject.utils.API_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AirViewModel @Inject constructor(
    private val repository: AirRepository,
    @Named ("token") private val token: String
) : ViewModel() {

    init {
        search("Rome")
    }

    fun search(city: String){
        viewModelScope.launch {
            val response = repository.search(
                token,
                city
            )
            Log.d("TAG1", response.toString())
        }
    }

}