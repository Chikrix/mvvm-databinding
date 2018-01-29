package com.tutor.proteins.placetalk.domain.repositories

import android.arch.lifecycle.MutableLiveData
import com.tutor.proteins.placetalk.domain.api.retrofit.RetrofitAdapter
import com.tutor.proteins.placetalk.domain.api.services.PlaceSearchService
import com.tutor.proteins.placetalk.domain.model.PlacesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OnlineRepository private constructor() {
  private val retrofitAdapter = RetrofitAdapter.RetrofitInstance.INSTANCE
  private val placeService by lazy {
    retrofitAdapter.createService(PlaceSearchService::class.java) as PlaceSearchService
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

  object OnlineRepoInstance {
    val INSTANCE by lazy {
      OnlineRepository()
    }
  }
}