package com.example.airqualityproject.presenter

import android.util.Log
import androidx.lifecycle.*
import com.example.airqualityproject.domain.model.Response
import com.example.airqualityproject.domain.repositories.AirRepository
import kotlinx.coroutines.launch

//@HiltViewModel
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
            Log.d("TAG1", result!!.value.toString())
        }
    }

}