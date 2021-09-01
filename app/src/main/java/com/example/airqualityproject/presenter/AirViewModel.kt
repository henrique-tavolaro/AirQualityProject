package com.example.airqualityproject.presenter

import android.util.Log
import androidx.lifecycle.*
import com.example.airqualityproject.domain.model.details.ResponseDetails
import com.example.airqualityproject.domain.model.search.Data
import com.example.airqualityproject.domain.model.search.Response
import com.example.airqualityproject.domain.repositories.AirRepository
import com.example.airqualityproject.presenter.detail_fragment.Pollutants
import com.example.airqualityproject.utils.Event
import com.github.aachartmodel.aainfographics.aachartcreator.AAOptions
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAChart
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AATitle
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAXAxis
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AirViewModel(
    private val repository: AirRepository,
    private val token: String
) : ViewModel() {

    val pollutantSelected : MutableLiveData<String> = MutableLiveData()
    val selected : LiveData<String> = pollutantSelected

    private val _result: MutableLiveData<Event<Response>> = MutableLiveData()
    val result: LiveData<Event<Response>> = _result

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading : LiveData<Boolean> = _loading

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage : LiveData<Event<String>> = _toastMessage

    fun search(city: String) {
        viewModelScope.launch {
            repository.search(
                city,
                token
            ).onEach { dataState ->

                _loading.value = dataState.loading

                dataState.data?.let {
                    _result.postValue(Event(it))
                }

                dataState.error?.let {
                    _toastMessage.value = Event("Error fetching data")
                }
            }.launchIn(viewModelScope)

        }
    }

    private val _navigateToAirQualityDetails = MutableLiveData<Data?>()
    val navigateToAirQualityDetails get() = _navigateToAirQualityDetails

    fun onCityClicked(data: Data) {
        _navigateToAirQualityDetails.value = data
    }

    fun onCityNavigated() {
        _navigateToAirQualityDetails.value = null
    }

    private val _detailsResult: MutableLiveData<Event<ResponseDetails>> = MutableLiveData()
    val detailsResult: LiveData<Event<ResponseDetails>> = _detailsResult

    fun getDetails(city: String) {
        viewModelScope.launch {
            repository.getDetails(city).onEach { dataState ->

                dataState.data?.let {
                    _detailsResult.postValue(Event(it))
                }

                dataState.error?.let {
                    _toastMessage.value = Event("Error fetching data")
                }
            }.launchIn(viewModelScope)

        }
    }

    fun createChart(result: ResponseDetails): AAOptions {
        val maxArray = mutableListOf<Int>()
        val minArray = mutableListOf<Int>()
        val daysArray = mutableListOf<String>()
        when (selected.value) {
            Pollutants.O3.name -> {
                for (i in result.data.forecast.daily.o3) {
                    maxArray.add(i.max)
                    daysArray.add(i.day)
                }
                for (i in result.data.forecast.daily.o3) {
                    minArray.add(i.min)
                }
            }
            Pollutants.PM10.name -> {
                for (i in result.data.forecast.daily.pm10) {
                    maxArray.add(i.max)
                    daysArray.add(i.day)
                }
                for (i in result.data.forecast.daily.pm10) {
                    minArray.add(i.min)
                }
            }
            Pollutants.PM25.name -> {
                for (i in result.data.forecast.daily.pm25) {
                    maxArray.add(i.max)
                    daysArray.add(i.day)
                }
                for (i in result.data.forecast.daily.pm25) {
                    minArray.add(i.min)
                }
            }
            Pollutants.UVI.name -> {
                for (i in result.data.forecast.daily.uvi) {
                    maxArray.add(i.max)
                    daysArray.add(i.day)
                }
                for (i in result.data.forecast.daily.uvi) {
                    minArray.add(i.min)
                }
            }
        }

        val aaXAxis = AAXAxis()
            .visible(true)
            .min(0f)
            .categories(daysArray.toTypedArray())

        return AAOptions()
            .chart(AAChart().backgroundColor("#ffffff"))
            .title(AATitle().text(if(selected.value != null) "${selected.value} Forecast" else "Choose pollutant" ))
            .xAxis(aaXAxis)
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Max")
                        .data(
                            maxArray.toTypedArray()
                        ),
                    AASeriesElement()
                        .name("Min")
                        .data(
                            minArray.toTypedArray()
                        ),
                )
            )
    }


}