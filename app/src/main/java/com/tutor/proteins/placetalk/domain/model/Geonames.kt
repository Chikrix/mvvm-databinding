package com.tutor.proteins.placetalk.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Geonames {

  @SerializedName("-style")
  @Expose
  var style: String? = null
  @SerializedName("totalResultsCount")
  @Expose
  var totalResultsCount: String? = null
  @SerializedName("geoname")
  @Expose
  var geoname: List<Geoname>? = null
}
