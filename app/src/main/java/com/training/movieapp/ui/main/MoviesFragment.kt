package com.training.movieapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentMoviesBinding
import com.training.movieapp.ui.main.adapter.movie.MainMovieAdapter
import com.training.movieapp.ui.main.utils.SampleData

class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private val binding: FragmentMoviesBinding by viewBinding(FragmentMoviesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvMainMovie.adapter = MainMovieAdapter(SampleData.collections)
        }
    }
}
