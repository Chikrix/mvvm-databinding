package com.tutor.proteins.placetalk.modules.home.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tutor.proteins.placetalk.R.layout
import com.tutor.proteins.placetalk.databinding.ActivityHomeBinding

class WeatherActivity : AppCompatActivity() {
  private lateinit var homeActivityBinding: ActivityHomeBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    homeActivityBinding = DataBindingUtil.setContentView(this,
        layout.activity_home)
  }
}
