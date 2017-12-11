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
  private val retrofitAdapter: RetrofitAdapter = RetrofitAdapter.INSTANCE
  private val weatherService: WeatherService by lazy {
    retrofitAdapter.createService(WeatherService::class.java) as WeatherService
  }
  private val placeService: PlaceSearchService by lazy {
    retrofitAdapter.createService(PlaceSearchService::class.java) as PlaceSearchService
  }

  fun fetchWeatherReport(lat: String, lng: String): MutableLiveData<WeatherResult> {
    val weatherResult: MutableLiveData<WeatherResult> = MutableLiveData()
    weatherService.fetchWeather(lat, lng).enqueue(object: Callback<WeatherResult>{
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
    val placeResult: MutableLiveData<PlacesList> = MutableLiveData()
    placeService.search(placeName).enqueue(object: Callback<PlacesList>{
      override fun onResponse(call: Call<PlacesList>?, response: Response<PlacesList>) {
        placeResult.value = response.body()
      }

      override fun onFailure(call: Call<PlacesList>?, t: Throwable?) {
        placeResult.value = null
      }

    })
    return placeResult
  }

  companion object OnlineRepoInstance {
    val INSTANCE: OnlineRepository by lazy {
      OnlineRepository()
    }
  }
}