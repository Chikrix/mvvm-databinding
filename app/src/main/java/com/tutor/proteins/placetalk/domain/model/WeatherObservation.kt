package com.tutor.proteins.placetalk.domain.model

import com.google.gson.annotations.SerializedName


data class WeatherObservation(
    @SerializedName("elevation")          val elevation: Int? = null,
    @SerializedName("lng")                val lng: Double? = null,
    @SerializedName("observation")        val observation: String? = null,
    @SerializedName("ICAO")               val iCAO: String? = null,
    @SerializedName("clouds")             val clouds: String? = null,
    @SerializedName("dewPoint")           val dewPoint: String? = null,
    @SerializedName("cloudsCode")         val cloudsCode: String? = null,
    @SerializedName("datetime")           val datetime: String? = null,
    @SerializedName("countryCode")        val countryCode: String? = null,
    @SerializedName("temperature")        val temperature: String? = null,
    @SerializedName("humidity")           val humidity: Int? = null,
    @SerializedName("stationName")        val stationName: String? = null,
    @SerializedName("weatherCondition")   val weatherCondition: String? = null,
    @SerializedName("windDirection")      val windDirection: Int? = null,
    @SerializedName("hectoPascAltimeter") val hectoPascAltimeter: Int? = null,
    @SerializedName("windSpeed")          val windSpeed: String? = null,
    @SerializedName("lat")                val lat: Double? = null)
