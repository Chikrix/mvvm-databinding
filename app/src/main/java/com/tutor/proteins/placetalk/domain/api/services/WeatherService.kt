package com.tutor.proteins.placetalk.domain.api.services

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

  @GET("findNearByWeatherJSON")
  fun fetchWeather(
      @Query("lat") latitude: String,
      @Query("lng") longitude: String,
      @Query("username") username: String)

}