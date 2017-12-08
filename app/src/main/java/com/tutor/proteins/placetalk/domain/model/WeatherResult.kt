package com.tutor.proteins.placetalk.domain.model

import com.google.gson.annotations.SerializedName


data class WeatherResult(
    @SerializedName("weatherObservation") val weatherObservation: WeatherObservation? = null)

