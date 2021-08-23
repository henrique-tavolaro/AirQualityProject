package com.example.airqualityproject.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airqualityproject.domain.repositories.AirRepository
import kotlinx.coroutines.launch

//@HiltViewModel
class AirViewModel(
    private val repository: AirRepository,
    private val token: String
) : ViewModel() {

    init {
        search("rome")
    }

    fun search(city: String){
        viewModelScope.launch {
            val response = repository.search(
                city,
                token
            )
            Log.d("TAG1", response.toString())
        }
    }

}