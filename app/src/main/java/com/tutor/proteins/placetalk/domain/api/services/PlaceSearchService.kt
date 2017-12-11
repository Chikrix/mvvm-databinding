package com.tutor.proteins.placetalk.domain.api.services

import com.tutor.proteins.placetalk.domain.model.PlacesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PlaceSearchService {

  @GET("wikipediaSearchJSON")
  fun search(
      @Query("q") query: String,
      @Query("username") username: String = "crisp"): Call<PlacesList>
}