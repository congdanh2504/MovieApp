package com.training.movieapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentExploreBinding
import com.training.movieapp.ui.main.adapter.explore.ExploreAdapter
import com.training.movieapp.ui.main.utils.SampleData

class ExploreFragment : Fragment(R.layout.fragment_explore) {
    private val binding: FragmentExploreBinding by viewBinding(FragmentExploreBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvMainExplore.adapter = ExploreAdapter(SampleData.listView)
        }
    }
}
