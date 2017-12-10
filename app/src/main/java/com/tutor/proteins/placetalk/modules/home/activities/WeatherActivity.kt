package com.tutor.proteins.placetalk.modules.home.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.tutor.proteins.placetalk.R
import com.tutor.proteins.placetalk.R.layout
import com.tutor.proteins.placetalk.databinding.WeatherActivityBinding
import com.tutor.proteins.placetalk.modules.home.fragments.WeatherFragment

class WeatherActivity: AppCompatActivity() {
  private lateinit var homeActivityBinding: WeatherActivityBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    homeActivityBinding = DataBindingUtil.setContentView(this,
        layout.weather_activity)
    navigateToFragment()
  }

  private fun navigateToFragment() {
    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.weatherActivityContainer, WeatherFragment.newInstance())
    fragmentTransaction.commit()
  }
}
