package com.tutor.proteins.placetalk.domain.repositories

import com.tutor.proteins.placetalk.domain.api.retrofit.RetrofitAdapter
import com.tutor.proteins.placetalk.domain.api.services.PlaceSearchService
import com.tutor.proteins.placetalk.domain.api.services.WeatherService
import com.tutor.proteins.placetalk.domain.model.LocationsList
import com.tutor.proteins.placetalk.domain.model.WeatherResult
import com.tutor.proteins.placetalk.util.RetrofitLiveData

class OnlineRepository private constructor() {
  private val retrofitAdapter: RetrofitAdapter = RetrofitAdapter.INSTANCE
  private val weatherService: WeatherService by lazy {
    retrofitAdapter.createService(WeatherService::class.java) as WeatherService
  }
  private val placeService: PlaceSearchService by lazy {
    retrofitAdapter.createService(WeatherService::class.java) as PlaceSearchService
  }

  fun fetchWeatherReport(lat: String, lng: String): RetrofitLiveData<WeatherResult> {
    return weatherService.fetchWeather(lat, lng)
  }

  fun fetchPlaceInfo(placeName: String): RetrofitLiveData<LocationsList> {
    return placeService.search(placeName)
  }

  companion object OnlineRepoInstance {
    val INSTANCE: OnlineRepository by lazy {
      OnlineRepository()
    }
  }
}