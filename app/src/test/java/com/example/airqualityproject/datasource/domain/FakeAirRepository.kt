package com.example.airqualityproject.datasource.domain

import com.example.airqualityproject.domain.model.DataState
import com.example.airqualityproject.domain.model.details.*
import com.example.airqualityproject.domain.model.search.Data
import com.example.airqualityproject.domain.model.search.Response
import com.example.airqualityproject.domain.model.search.Station
import com.example.airqualityproject.domain.model.search.Time
import com.example.airqualityproject.domain.repositories.AirRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAirRepository : AirRepository {
    override suspend fun search(city: String, token: String): Flow<DataState<Response?>> = flow {
        if(city == "honolulu"){
            emit(DataState.success(response))
        } else  {
            emit(DataState.error<Response>("Error"))
        }
    }

    override suspend fun getDetails(city: String): Flow<DataState<ResponseDetails?>> = flow {
        if(city == "honolulu"){
            emit(DataState.success(detailsResponse))
        } else  {
            emit(DataState.error<ResponseDetails>("Error"))
        }
    }
}

val response = Response(data=listOf(Data(aqi="38", station= Station(country="US", geo= listOf(21.30383819, 157.8711711),
    name="honalulu; Sand Island, Hawaii, USA", url="usa/hawaii/sand-island"), time= Time(stime="2021-08-31 13:00:00",
    tz="-10:00", vtime=1630450800), uid=5273), Data(aqi="8", station=Station(country="US", geo= listOf(21.30758, 157.85542),
    name="honalulu; Honolulu, Hawaii, USA", url="usa/hawaii/honolulu"), time=Time(stime="2021-08-31 13:00:00", tz="-10:00",
    vtime=1630450800), uid=5268)), status="ok")

val detailsResponse = ResponseDetails(status="ok", data=com.example.airqualityproject.domain.model.details.Data(aqi=8,
    attributions=listOf(Attribution(logo="US-Hawaii.png", name="State of Hawaii, Department of Health, Clean Air Branch",
        url="http://health.hawaii.gov/cab/"), Attribution(logo="null", name="Air Now - US EPA", url="http://www.airnow.gov/"),
        Attribution(logo="null", name="World Air Quality Index Project", url="https://waqi.info/")), city= City(geo=listOf(21.30758, 157.85542),
        name="Honolulu, Hawaii, USA", url="https://aqicn.org/city/usa/hawaii/honolulu"), debug= Debug(sync="2021-09-01T08:23:22+09:00"),
    dominentpol="pm10", forecast=Forecast(daily=Daily(o3=listOf(O3(avg=12, day="2021-08-29", max=14, min=11), O3(avg=15, day="2021-08-30",
        max=17, min=13), O3(avg=14, day="2021-08-31", max=17, min=11), O3(avg=10, day="2021-09-01", max=13, min=9), O3(avg=8,
        day="2021-09-02", max=12, min=5), O3(avg=13, day="2021-09-03", max=16, min=10), O3(avg=15, day="2021-09-04", max=20, min=14),
        O3(avg=16, day="2021-09-05", max=19, min=15)), pm10=listOf(Pm10(avg=8, day="2021-08-29", max=10, min=6), Pm10(avg=8,
        day="2021-08-30", max=10, min=7), Pm10(avg=7, day="2021-08-31", max=7, min=6), Pm10(avg=7, day="2021-09-01", max=10, min=4),
        Pm10(avg=13, day="2021-09-02", max=16, min=9), Pm10(avg=17, day="2021-09-03", max=22, min=14), Pm10(avg=13, day="2021-09-04", max=16,
            min=11), Pm10(avg=14, day="2021-09-05", max=16, min=9)), pm25=listOf(Pm25(avg=20, day="2021-08-29", max=25, min=16), Pm25(avg=18,
        day="2021-08-30", max=25, min=15), Pm25(avg=19, day="2021-08-31", max=23, min=15), Pm25(avg=10, day="2021-09-01", max=15, min=8),
        Pm25(avg=17, day="2021-09-02", max=28, min=9), Pm25(avg=25, day="2021-09-03", max=31, min=18), Pm25(avg=25, day="2021-09-04", max=30,
            min=14), Pm25(avg=21, day="2021-09-05", max=28, min=11)), uvi=listOf(Uvi(avg=2, day="2021-08-31", max=11, min=0), Uvi(avg=2,
        day="2021-09-01", max=10, min=0), Uvi(avg=2, day="2021-09-02", max=11, min=0), Uvi(avg=2, day="2021-09-03", max=12, min=0),
        Uvi(avg=2, day="2021-09-04", max=10, min=0), Uvi(avg=0, day="2021-09-05", max=0, min=0)))), iaqi=Iaqi(co=Co(v=5.6), dew=Dew(v=19),
        h=H(v=54.0f), p=P(v=1013.2), pm10=Pm10X(v=8), pm25=Pm25X(v=5), t=T(v=29.0f), w=W(v=1.0f), wg=Wg(v=9.2)), idx=5268, timeDetails=null))
