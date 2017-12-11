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

  private var onlineRepository: OnlineRepository = OnlineRepository.INSTANCE
  val shouldShowEmptyScreenState: ObservableField<Boolean> = ObservableField(true)
  val shouldSetButtonNotClickable: ObservableField<Boolean> = ObservableField(true)

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
  }

}