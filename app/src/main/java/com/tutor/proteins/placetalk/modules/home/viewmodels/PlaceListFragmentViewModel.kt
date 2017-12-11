package com.tutor.proteins.placetalk.modules.home.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.databinding.ObservableField
import com.tutor.proteins.placetalk.domain.model.Geoname
import com.tutor.proteins.placetalk.domain.model.PlacesList
import com.tutor.proteins.placetalk.domain.model.WeatherResult
import com.tutor.proteins.placetalk.domain.repositories.OnlineRepository

class PlaceListFragmentViewModel(context: Application = Application()): AndroidViewModel(context) {

  private var onlineRepository = OnlineRepository.INSTANCE
  val shouldShowEmptyScreenState = ObservableField(true)
  val shouldSetButtonNotClickable = ObservableField(true)
  val shouldHideProgressBar = ObservableField(true)
  var count = 7

  fun setSearchState(state: Boolean) {
    shouldShowEmptyScreenState.set(state)
    shouldSetButtonNotClickable.set(state)
  }

  fun getWeatherInformation(lat: String, lon: String): LiveData<WeatherResult> {
    return onlineRepository.fetchWeatherReport(lat, lon)
  }

  fun getPlaceInformation(place: String): LiveData<PlacesList> {
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