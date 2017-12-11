package com.tutor.proteins.placetalk.domain.model

import com.google.gson.annotations.SerializedName

class PlacesList {

  @SerializedName("geonames")
  var geonames: List<Geoname>? = null

}
