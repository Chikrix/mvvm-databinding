package com.tutor.proteins.placetalk.domain.api.services

import retrofit2.http.GET
import retrofit2.http.Query


interface PlaceSearchService {

  @GET("searchJSON")
  fun search(
      @Query("q") query: String,
      @Query("username") username: String)
}