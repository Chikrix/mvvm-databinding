package com.tutor.proteins.placetalk.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Geoname: Serializable {

  @SerializedName("countryCode")
  var countryCode: String? = null
  @SerializedName("elevation")
  var elevation: Long? = null
  @SerializedName("feature")
  var feature: String? = null
  @SerializedName("geoNameId")
  var geoNameId: Long? = null
  @SerializedName("lang")
  var lang: String? = null
  @SerializedName("lat")
  var lat: Double? = null
  @SerializedName("lng")
  var lng: Double? = null
  @SerializedName("rank")
  var rank: Long? = null
  @SerializedName("summary")
  var summary: String? = null
  @SerializedName("thumbnailImg")
  var thumbnailImg: String? = null
  @SerializedName("title")
  var title: String? = null
  @SerializedName("wikipediaUrl")
  var wikipediaUrl: String? = null

}
