package com.training.movieapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentSeriesBinding
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.main.adapter.series.MainSeriesAdapter
import com.training.movieapp.ui.main.model.MainSeries
import com.training.movieapp.ui.main.utils.Trending
import com.training.movieapp.ui.main.viewmodel.SeriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeriesFragment : Fragment(R.layout.fragment_series) {
    private val binding: FragmentSeriesBinding by viewBinding(FragmentSeriesBinding::bind)
    private val seriesViewModel: SeriesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
        initObservers()
    }

    private fun initActions() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            seriesViewModel.getSeries()
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                seriesViewModel.mainSeriesState.collect { state ->
                    when (state) {
                        is DataState.Success -> {
                            binding.swipeRefreshLayout.isRefreshing = false
                            setSeries(state.data)
                        }

                        is DataState.Error -> {
                            binding.swipeRefreshLayout.isRefreshing = false
                            Toast.makeText(
                                requireContext(),
                                state.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun setSeries(data: com.training.movieapp.domain.model.TopSeries) {
        val collections = ArrayList<MainSeries>()
        collections.add(MainSeries("Trending Mow", data.trendingSeries.results, Trending.TRUE))
        collections.add(
            MainSeries(
                "Popular",
                data.popularSeries.results.sortedBy { it.id },
                Trending.FALSE
            )
        )
        collections.add(MainSeries("Airing Today", data.airingTodaySeries.results, Trending.FALSE))
        collections.add(
            MainSeries(
                "On The Air",
                data.onTheAirSeries.results.shuffled(),
                Trending.FALSE
            )
        )
        binding.apply {
            rvMainSeries.adapter = MainSeriesAdapter(collections, onSeriesClick)
        }
    }

    private val onSeriesClick: (seriesId: Int) -> Unit = { seriesId ->
        val action = SeriesFragmentDirections.actionSeriesFragmentToSeriesDetailFragment(seriesId)
        findNavController().navigate(action)
    }
}
