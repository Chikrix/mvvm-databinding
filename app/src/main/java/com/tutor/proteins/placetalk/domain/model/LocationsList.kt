package com.tutor.proteins.placetalk.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LocationsList {
  @SerializedName("geonames")
  @Expose
  var geonames: List<Geoname>? = null
}
