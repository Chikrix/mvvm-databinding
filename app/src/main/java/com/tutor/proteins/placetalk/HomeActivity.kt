package com.tutor.proteins.placetalk

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tutor.proteins.placetalk.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
  private lateinit var homeActivityBinding: ActivityHomeBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    homeActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
  }
}
