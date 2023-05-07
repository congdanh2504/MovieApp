package com.training.movieapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentMoviesBinding
import com.training.movieapp.domain.model.Movies
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.main.adapter.movie.MainMovieAdapter
import com.training.movieapp.ui.main.model.MainMovie
import com.training.movieapp.ui.main.utils.Trending
import com.training.movieapp.ui.main.viewmodel.MoviesViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val binding: FragmentMoviesBinding by viewBinding(FragmentMoviesBinding::bind)
    private lateinit var dialog: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = LoadingDialog(requireContext())
        moviesViewModel.getMovies()
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesViewModel.moviesState.collect { state ->
                    when (state) {
                        is DataState.Idle -> {
                            dialog.dismiss()
                        }

                        is DataState.Loading -> {
                            dialog.show()
                        }

                        is DataState.Success -> {
                            dialog.dismiss()

                            setMovies(state.data)
                        }

                        is DataState.Error -> {
                            dialog.dismiss()
                            Toast.makeText(
                                requireContext(),
                                state.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun setMovies(data: Movies) {
        val colections = ArrayList<MainMovie>()
        colections.add(MainMovie("Trending Mow", data.trendingMovies.results, Trending.TRUE))
        colections.add(MainMovie("Now Playing", data.nowPlayingMovies.results, Trending.FALSE))
        colections.add(MainMovie("Up Coming", data.upComingMovies.results, Trending.FALSE))
        colections.add(MainMovie("Top Rated", data.topRatedMovies.results, Trending.FALSE))
        binding.apply {
            rvMainMovie.adapter = MainMovieAdapter(colections)
        }
    }
}
