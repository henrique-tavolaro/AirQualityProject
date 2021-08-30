package com.example.airqualityproject.presenter

import android.util.Log
import androidx.lifecycle.*
import com.example.airqualityproject.domain.model.search.Data
import com.example.airqualityproject.domain.model.search.Response
import com.example.airqualityproject.domain.repositories.AirRepository
import kotlinx.coroutines.launch

class AirViewModel(
    private val repository: AirRepository,
    private val token: String
) : ViewModel() {

    private val _result: MutableLiveData<Response> = MutableLiveData()
    val result: LiveData<Response> get() = _result

    fun search(city: String) {
        viewModelScope.launch {
            _result.value = repository.search(
                city,
                token
            )
        }
    }

    private val _navigateToAirQualityDetails = MutableLiveData<Data?>()
    val navigateToAirQualityDetails get() = _navigateToAirQualityDetails

    fun onCityClicked(data: Data) {
        _navigateToAirQualityDetails.value = data
    }

    fun onCityAirQualityDetailsNavigated(){
        _navigateToAirQualityDetails.value = null
    }



    fun getDetails(city: String){
        viewModelScope.launch {
            val result = repository.getDetails(city)
            Log.d("TAG111", result.toString())
        }
    }

}