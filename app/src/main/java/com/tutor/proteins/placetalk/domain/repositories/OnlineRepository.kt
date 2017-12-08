package com.tutor.proteins.placetalk.domain.repositories

import com.tutor.proteins.placetalk.domain.api.retrofit.RetrofitAdapter

class OnlineRepository {
  private val retrofitAdapter: RetrofitAdapter = RetrofitAdapter.getInstance()

  init {
    //retrofitAdapter =
  }

  /**
   * Singleton object of online store
   */
  object OnlineRepoInstance {
    fun getInstance(): OnlineRepository = OnlineRepository()
  }
}