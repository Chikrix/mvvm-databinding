package com.tutor.proteins.placetalk.domain.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitAdapter private constructor() {
  private val retrofit: Retrofit

  init {

    retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
  }

  fun createService(clazz: Class<*>): Any {
    return retrofit.create(clazz)
  }

  companion object RetrofitInstance {

    val INSTANCE: RetrofitAdapter by lazy {
      RetrofitAdapter()
    }

    const val BASE_URL = "http://api.geonames.org/"
  }

}