package com.tutor.proteins.placetalk.domain.repositories

import android.arch.lifecycle.MutableLiveData
import com.tutor.proteins.placetalk.domain.api.retrofit.RetrofitAdapter
import com.tutor.proteins.placetalk.domain.api.services.PlaceSearchService
import com.tutor.proteins.placetalk.domain.api.services.WeatherService
import com.tutor.proteins.placetalk.domain.model.PlacesList
import com.tutor.proteins.placetalk.domain.model.WeatherResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OnlineRepository private constructor() {
  private val retrofitAdapter = RetrofitAdapter.INSTANCE
  private val weatherService by lazy {
    retrofitAdapter.createService(WeatherService::class.java) as WeatherService
  }
  private val placeService by lazy {
    retrofitAdapter.createService(PlaceSearchService::class.java) as PlaceSearchService
  }

  fun fetchWeatherReport(lat: String, lng: String): MutableLiveData<WeatherResult> {
    val weatherResult = MutableLiveData<WeatherResult>()
    weatherService.fetchWeather(lat, lng).enqueue(object : Callback<WeatherResult> {
      override fun onResponse(call: Call<WeatherResult>?, response: Response<WeatherResult>) {
        weatherResult.value = response.body()
      }

      override fun onFailure(call: Call<WeatherResult>?, t: Throwable?) {
        weatherResult.value = null
      }
    })

    return weatherResult
  }

  fun fetchPlaceInfo(placeName: String): MutableLiveData<PlacesList> {
    val placeResult = MutableLiveData<PlacesList>()
    placeService.search(placeName).enqueue(object : Callback<PlacesList> {
      override fun onResponse(call: Call<PlacesList>?, response: Response<PlacesList>) {
        placeResult.value = response.body()
      }

      override fun onFailure(call: Call<PlacesList>?, t: Throwable?) {
        // TODO Handle error cases. Like wrap a custom object that holds the data and a throwable around a LiveData
        placeResult.value = null
      }

    })

    return placeResult
  }

  companion object OnlineRepoInstance {
    val INSTANCE by lazy {
      OnlineRepository()
    }
  }
}