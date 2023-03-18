package com.training.movieapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.movieapp.databinding.FragmentSeriesBinding
import com.training.movieapp.ui.main.adapter.movie.series.MainSeriesAdapter
import com.training.movieapp.ui.main.utils.SampleData

class SeriesFragment : Fragment() {
    private lateinit var binding:FragmentSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSeriesBinding.inflate(inflater, container, false)
        binding.apply {
            rvMainSeries.adapter = MainSeriesAdapter(SampleData.collectionsSeries)
        }
        return binding.root
    }
}
