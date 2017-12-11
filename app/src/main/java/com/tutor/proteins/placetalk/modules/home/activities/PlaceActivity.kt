package com.tutor.proteins.placetalk.modules.home.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.tutor.proteins.placetalk.R
import com.tutor.proteins.placetalk.R.layout
import com.tutor.proteins.placetalk.databinding.PlaceActivityBinding
import com.tutor.proteins.placetalk.modules.home.fragments.PlaceListFragment

class PlaceActivity: AppCompatActivity() {
  private lateinit var placeActivityBinding: PlaceActivityBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    placeActivityBinding = DataBindingUtil.setContentView(this,
        layout.place_activity)
    navigateToFragment()
  }

  private fun navigateToFragment() {
    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.placeActivityContainer, PlaceListFragment.newInstance())
    fragmentTransaction.commit()
  }
  
}
