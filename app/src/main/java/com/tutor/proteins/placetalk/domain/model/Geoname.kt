package com.tutor.proteins.placetalk.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Geoname {

  @SerializedName("toponymName")
  @Expose
  var toponymName: String? = null
  @SerializedName("name")
  @Expose
  var name: String? = null
  @SerializedName("lat")
  @Expose
  var lat: String? = null
  @SerializedName("lng")
  @Expose
  var lng: String? = null
  @SerializedName("geonameId")
  @Expose
  var geonameId: String? = null
  @SerializedName("countryCode")
  @Expose
  var countryCode: String? = null
  @SerializedName("countryName")
  @Expose
  var countryName: String? = null
  @SerializedName("fcl")
  @Expose
  var fcl: String? = null
  @SerializedName("fcode")
  @Expose
  var fcode: String? = null
}
