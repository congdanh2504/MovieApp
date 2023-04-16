package com.training.movieapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentSeriesBinding
import com.training.movieapp.ui.main.adapter.series.MainSeriesAdapter
import com.training.movieapp.ui.main.utils.SampleData

class SeriesFragment : Fragment(R.layout.fragment_series) {
    private val binding: FragmentSeriesBinding by viewBinding(FragmentSeriesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvMainSeries.adapter = MainSeriesAdapter(SampleData.collectionsSeries)
        }
    }
}
