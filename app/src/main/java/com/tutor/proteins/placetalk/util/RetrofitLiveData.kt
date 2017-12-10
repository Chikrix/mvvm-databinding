package com.tutor.proteins.placetalk.util

import android.arch.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Usually, I'll use Rxjava2ConverterFactory when building the Retrofit instance. But I don't want
 * to use any factory to make it simpler.
 *
 * Courtesy
 * https://medium.com/@alvaro.blanco/playing-with-android-architecture-components-retrofit-part-1-9994d651cf3c
 */

class RetrofitLiveData<T>(private val call: Call<T>) : LiveData<T>(), Callback<T> {

  override fun onActive() {
    if (!call.isCanceled && !call.isExecuted) call.enqueue(this)
  }

  override fun onFailure(call: Call<T>?, t: Throwable?) {
    //not implemented
  }

  override fun onResponse(call: Call<T>?, response: Response<T>?) {
    value = response?.body()
  }

  fun cancel() = if(!call.isCanceled) call.cancel() else Unit
}