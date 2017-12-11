package com.tutor.proteins.placetalk.modules.home.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutor.proteins.placetalk.R
import com.tutor.proteins.placetalk.databinding.PlaceItemBinding
import com.tutor.proteins.placetalk.databinding.PlaceListFragmentBinding
import com.tutor.proteins.placetalk.domain.model.Geoname
import com.tutor.proteins.placetalk.modules.home.viewmodels.WeatherFragmentViewModel


class PlaceListFragment: Fragment() {
  private lateinit var weatherFragmentViewModel: WeatherFragmentViewModel
  private lateinit var weatherFragmentBinding: PlaceListFragmentBinding
  private lateinit var adapter: PlacesListFragmentAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    weatherFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.place_list_fragment, container,
        false)
    weatherFragmentBinding.placeListFragmentRv.layoutManager = LinearLayoutManager(context)
    adapter = PlacesListFragmentAdapter()
    weatherFragmentBinding.placeListFragmentRv.adapter = adapter
    weatherFragmentBinding.placeListFragmentRv.setHasFixedSize(true)
    return weatherFragmentBinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    weatherFragmentViewModel = ViewModelProviders.of(this).get(WeatherFragmentViewModel::class.java)
    weatherFragmentBinding.viewModel = weatherFragmentViewModel
    weatherFragmentBinding.handler = Handler()
    setListeners()
  }

  private fun setListeners() {
    weatherFragmentBinding.placeListFragmentEtSearch.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isNotEmpty()) weatherFragmentViewModel.setSearchState(false)
        else weatherFragmentViewModel.setSearchState(true)
      }
    })
  }

  @SuppressWarnings("unused")
  inner class Handler {

    fun onSearchForPlace(view: View) {
      val placeQuery = weatherFragmentBinding.placeListFragmentEtSearch.text.toString()
      weatherFragmentViewModel.getPlaceInformation(placeQuery).observe(this@PlaceListFragment,
          Observer { locationList ->
            adapter.updateData(locationList?.geonames)
          })
    }

  }

  inner class PlacesListFragmentAdapter:
      RecyclerView.Adapter<PlacesListFragmentAdapter.PlaceViewHolder>() {

    private var placesList: List<Geoname> = listOf()

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
      holder.setPlaceInfo(placesList[position])
    }

    fun updateData(placesList: List<Geoname>?) {
      placesList ?: return
      this.placesList = placesList
      notifyDataSetChanged()
    }

    override fun getItemCount(): Int = placesList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlaceViewHolder {
      val placeInfoItemBinding: PlaceItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent?.context),
          R.layout.place_item, parent, false)
      return PlaceViewHolder(placeInfoItemBinding)
    }

    inner class PlaceViewHolder(private val placeItemBinding: PlaceItemBinding):
        RecyclerView.ViewHolder(placeItemBinding.root) {

      fun setPlaceInfo(geoname: Geoname) {
        placeItemBinding.placeInfo = geoname
        placeItemBinding.executePendingBindings()
      }
    }
  }

  companion object {
    fun newInstance() = PlaceListFragment()
  }
}