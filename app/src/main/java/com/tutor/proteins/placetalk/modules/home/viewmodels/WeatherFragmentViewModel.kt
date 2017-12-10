package com.tutor.proteins.placetalk.modules.home.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.tutor.proteins.placetalk.domain.repositories.OnlineRepository

class WeatherFragmentViewModel(context: Application): AndroidViewModel(context) {

  private val onlineRepository: OnlineRepository = OnlineRepository.INSTANCE

  override fun onCleared() {

  }

}