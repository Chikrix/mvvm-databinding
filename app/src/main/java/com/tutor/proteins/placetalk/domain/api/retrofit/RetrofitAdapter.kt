package com.tutor.proteins.placetalk.domain.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitAdapter private constructor() {
  private val retrofit: Retrofit
  private val BASE_URL = "http://api.geonames.org/"

  init {

    retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
  }

  fun createService(clazz: Class<*>): Any {
    return retrofit.create(clazz)
  }

  object RetrofitInstance {

    val INSTANCE by lazy {
      RetrofitAdapter()
    }
  }

}