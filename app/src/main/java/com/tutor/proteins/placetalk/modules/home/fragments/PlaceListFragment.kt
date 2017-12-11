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
import android.widget.Toast
import com.tutor.proteins.placetalk.R
import com.tutor.proteins.placetalk.R.string
import com.tutor.proteins.placetalk.UNUSED
import com.tutor.proteins.placetalk.databinding.PlaceItemBinding
import com.tutor.proteins.placetalk.databinding.PlaceListFragmentBinding
import com.tutor.proteins.placetalk.domain.model.Geoname
import com.tutor.proteins.placetalk.modules.home.viewmodels.PlaceListFragmentViewModel


class PlaceListFragment : Fragment(), PlaceListFragmentViewModel.ViewActions {
  private lateinit var weatherFragmentViewModel: PlaceListFragmentViewModel
  private lateinit var weatherFragmentBinding: PlaceListFragmentBinding
  private lateinit var adapter: PlacesListFragmentAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    weatherFragmentBinding =
        DataBindingUtil.inflate(inflater, R.layout.place_list_fragment, container, false)
    setupListView()
    return weatherFragmentBinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    weatherFragmentViewModel = ViewModelProviders.of(this).get(
        PlaceListFragmentViewModel::class.java)
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

  private fun setupListView() {
    weatherFragmentBinding.placeListFragmentRv.layoutManager = LinearLayoutManager(context)
    adapter = PlacesListFragmentAdapter()
    weatherFragmentBinding.placeListFragmentRv.adapter = adapter
    weatherFragmentBinding.placeListFragmentRv.setHasFixedSize(true)
  }

  override fun onPlaceItemSelected(geoname: Geoname?) {
    Toast.makeText(context, getString(string.no_action_text), Toast.LENGTH_SHORT).show()
    /*geoname ?: return
    val bundle = Bundle()
    bundle.apply {
      bundle.putSerializable(Constants.PLACE_ITEM, geoname)
    }

    (context as PlaceActivity).openWeatherInfoFragment()
    */
  }

  override fun handleErrors(exc: Exception) {
    when (exc) {
      is IllegalStateException,
      is IllegalArgumentException -> Toast.makeText(context, getString(string.nothing_found),
          Toast.LENGTH_SHORT).show()
    }
  }

  override fun onResume() {
    super.onResume()

    Toast.makeText(context, "Count is ${weatherFragmentViewModel.count}", Toast.LENGTH_SHORT).show()
  }

  @SuppressWarnings(UNUSED)
  inner class Handler {

    fun onSearchForPlace(view: View) {
      weatherFragmentViewModel.shouldHideProgressBar.set(false)
      val placeQuery = weatherFragmentBinding.placeListFragmentEtSearch.text.toString()
      weatherFragmentViewModel.getPlaceInformation(placeQuery).observe(this@PlaceListFragment,
          Observer { locationList ->
            try {
              weatherFragmentViewModel.shouldHideProgressBar.set(true)
              val geonames = locationList?.geonames
              val locations = requireNotNull(geonames)
              check(locations.isNotEmpty())
              weatherFragmentViewModel.count = locations.size
              adapter.updateData(locations)
            } catch (err: Exception) {
              handleErrors(err)
            }
          })
    }

  }

  private inner class PlacesListFragmentAdapter :
      RecyclerView.Adapter<PlacesListFragmentAdapter.PlaceViewHolder>() {

    private var placesList = listOf<Geoname>()

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
      holder.setPlaceInfo(placesList[position])
    }

    fun updateData(placesList: List<Geoname>) {
      this.placesList = placesList
      notifyDataSetChanged()
    }

    override fun getItemCount(): Int = placesList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlaceViewHolder {
      val placeInfoItemBinding = DataBindingUtil.inflate<PlaceItemBinding>(
          LayoutInflater.from(parent?.context), R.layout.place_item, parent, false)
      return PlaceViewHolder(placeInfoItemBinding)
    }

    inner class PlaceViewHolder(private val placeItemBinding: PlaceItemBinding) :
        RecyclerView.ViewHolder(placeItemBinding.root), View.OnClickListener {

      init {
        placeItemBinding.placeItemCv.setOnClickListener(this)
      }

      fun setPlaceInfo(geoname: Geoname) {
        placeItemBinding.placeInfo = geoname
        placeItemBinding.executePendingBindings()
      }

      override fun onClick(v: View) {
        onPlaceItemSelected(placeItemBinding.placeInfo)
      }
    }
  }

  companion object {
    fun newInstance() = PlaceListFragment()
  }
}