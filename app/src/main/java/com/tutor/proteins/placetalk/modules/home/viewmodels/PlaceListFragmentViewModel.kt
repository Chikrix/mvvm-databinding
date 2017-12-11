package com.tutor.proteins.placetalk.modules.home.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.tutor.proteins.placetalk.domain.model.Geoname
import com.tutor.proteins.placetalk.domain.model.PlacesList
import com.tutor.proteins.placetalk.domain.model.WeatherResult
import com.tutor.proteins.placetalk.domain.repositories.OnlineRepository

class PlaceListFragmentViewModel: ViewModel() {

  private var onlineRepository = OnlineRepository.INSTANCE
  val shouldShowEmptyScreenState = ObservableField(true)
  val shouldHideProgressBar = ObservableField(true)
  var locationsList = MutableLiveData<PlacesList>()

  fun setSearchState(state: Boolean) {
    shouldShowEmptyScreenState.set(state)
  }

  fun getWeatherInformation(lat: String, lon: String): LiveData<WeatherResult> {
     return onlineRepository.fetchWeatherReport(lat, lon)
  }

  fun getPlaceInformation(place: String): LiveData<PlacesList> {
    locationsList = onlineRepository.fetchPlaceInfo(place)
    return onlineRepository.fetchPlaceInfo(place)
  }

  override fun onCleared() {
    // Clear up resources which could cause a leak
  }

  interface ViewActions {

    fun onPlaceItemSelected(geoname: Geoname?)

    // Could be used to handle different error cases from livedata
    fun handleErrors(exc: Exception)
  }

}