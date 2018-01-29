package com.tutor.proteins.placetalk.modules.home.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import com.tutor.proteins.placetalk.util.setTextChangeAction
import com.tutor.proteins.placetalk.util.showToast


class PlaceListFragment : Fragment(), PlaceListFragmentViewModel.ViewActions {
  private lateinit var placeListFragmentViewModel: PlaceListFragmentViewModel
  private lateinit var placeListFragmentBinding: PlaceListFragmentBinding
  private lateinit var adapter: PlacesListFragmentAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    placeListFragmentBinding =
        DataBindingUtil.inflate(inflater, R.layout.place_list_fragment, container, false)
    setupListView()
    return placeListFragmentBinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    placeListFragmentViewModel = ViewModelProviders.of(this).get(
        PlaceListFragmentViewModel::class.java)

    with(placeListFragmentBinding) {
      viewModel = placeListFragmentViewModel
      handler = Handler()
      setListeners(this)
    }
  }

  private fun setListeners(binding: PlaceListFragmentBinding) {
    binding.placeListFragmentEtSearch.setTextChangeAction { setSearchState(it) }
  }

  private fun setSearchState(textEntry: CharSequence) {
    if (textEntry.isNotEmpty()) placeListFragmentViewModel.setSearchState(false)
  }

  private fun setupListView() {
    with(placeListFragmentBinding) {
      placeListFragmentRv.layoutManager = LinearLayoutManager(context)
      adapter = PlacesListFragmentAdapter()
      placeListFragmentRv.adapter = adapter
      placeListFragmentRv.setHasFixedSize(true)
    }
  }

  override fun onPlaceItemSelected(geoname: Geoname?) {
    context.showToast(string.no_action_text)
  }

  override fun handleErrors(exc: Exception) {
    when (exc) {
      is IllegalStateException,
      is IllegalArgumentException -> context.showToast(string.nothing_found)

    }
  }

  @SuppressWarnings(UNUSED)
  inner class Handler {

    fun onSearchForPlace(view: View) {
      placeListFragmentViewModel.shouldHideProgressBar.set(false)
      val placeQuery = placeListFragmentBinding.placeListFragmentEtSearch.text.toString()
      placeListFragmentViewModel.getPlaceInformation(placeQuery).observe(this@PlaceListFragment,
          Observer { locationList ->
            try {
              placeListFragmentViewModel.shouldHideProgressBar.set(true)
              val geonames = locationList?.geonames
              val locations = requireNotNull(geonames)
              check(locations.isNotEmpty())
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
        with(placeItemBinding) {
          placeInfo = geoname
          executePendingBindings()
        }
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