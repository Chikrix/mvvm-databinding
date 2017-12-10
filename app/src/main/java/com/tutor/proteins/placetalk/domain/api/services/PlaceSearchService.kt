package com.tutor.proteins.placetalk.domain.api.services

import com.tutor.proteins.placetalk.domain.model.LocationsList
import com.tutor.proteins.placetalk.util.RetrofitLiveData
import retrofit2.http.GET
import retrofit2.http.Query


interface PlaceSearchService {

  @GET("searchJSON")
  fun search(
      @Query("q") query: String,
      @Query("username") username: String = "crisp"): RetrofitLiveData<LocationsList>
}