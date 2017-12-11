package com.tutor.proteins.placetalk.util

import android.databinding.BindingAdapter
import android.widget.TextView
import com.tutor.proteins.placetalk.domain.model.Geoname

@BindingAdapter("app:place")
fun setPlaceName(view: TextView, place: Geoname) {
  val name = "${place.title}, ${place.countryCode}"
  view.text = name
}