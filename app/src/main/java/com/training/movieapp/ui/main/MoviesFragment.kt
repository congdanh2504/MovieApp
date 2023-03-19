package com.training.movieapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.movieapp.databinding.FragmentMoviesBinding
import com.training.movieapp.ui.main.adapter.movie.MainMovieAdapter
import com.training.movieapp.ui.main.utils.SampleData

class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.apply {
            rvMainMovie.adapter = MainMovieAdapter(SampleData.collections)
        }
        return binding.root
    }
}
