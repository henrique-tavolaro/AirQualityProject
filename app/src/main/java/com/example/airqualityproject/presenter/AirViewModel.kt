package com.example.airqualityproject.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airqualityproject.domain.model.details.ResponseDetails
import com.example.airqualityproject.domain.model.search.Data
import com.example.airqualityproject.domain.model.search.Response
import com.example.airqualityproject.domain.repositories.AirRepository
import com.example.airqualityproject.presenter.detail_fragment.Pollutants
import com.github.aachartmodel.aainfographics.aachartcreator.AAOptions
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAChart
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AATitle
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAXAxis
import kotlinx.coroutines.launch

class AirViewModel(
    private val repository: AirRepository,
    private val token: String
) : ViewModel() {

    var selected = ""

    private val _result: MutableLiveData<Response> = MutableLiveData()
    val result: LiveData<Response> = _result

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

    private val _detailsResult: MutableLiveData<ResponseDetails> = MutableLiveData()
    val detailsResult: LiveData<ResponseDetails> = _detailsResult

    fun getDetails(city: String) {
        viewModelScope.launch {
            _detailsResult.value = repository.getDetails(city)
        }
    }

    fun createChart(result: ResponseDetails): AAOptions {
        val maxArray = mutableListOf<Int>()
        val minArray = mutableListOf<Int>()
        val daysArray = mutableListOf<String>()
        when (selected) {
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
                for (i in result!!.data.forecast.daily.uvi) {
                    maxArray.add(i.max)
                    daysArray.add(i.day)
                }
                for (i in result!!.data.forecast.daily.uvi) {
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
            .title(AATitle().text("$selected Forecast"))
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