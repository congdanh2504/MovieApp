package com.training.movieapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentMoviesBinding
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.Movies
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.main.adapter.movie.MainMovieAdapter
import com.training.movieapp.ui.main.model.MainMovie
import com.training.movieapp.ui.main.utils.Trending
import com.training.movieapp.ui.main.viewmodel.MainViewModel
import com.training.movieapp.ui.main.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val binding: FragmentMoviesBinding by viewBinding(FragmentMoviesBinding::bind)
    private lateinit var dialog: LoadingDialog
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel.getMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = LoadingDialog(requireContext())
        mainViewModel.readUser()
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesViewModel.moviesState.collect { state ->
                    when (state) {
                        is DataState.Success -> {
                            setMovies(state.data)
                        }

                        is DataState.Error -> {
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

    private fun setMovies(data: Movies) {
        val collections = ArrayList<MainMovie>()
        collections.add(MainMovie("Trending Mow", data.trendingMovies.results, Trending.TRUE))
        collections.add(MainMovie("Now Playing", data.nowPlayingMovies.results, Trending.FALSE))
        collections.add(MainMovie("Up Coming", data.upComingMovies.results, Trending.FALSE))
        collections.add(MainMovie("Top Rated", data.topRatedMovies.results, Trending.FALSE))
        binding.apply {
            rvMainMovie.adapter = MainMovieAdapter(collections, onMovieClick)
        }
    }

    private val onMovieClick: (movie: Movie) -> Unit = { movie ->
        val action = MoviesFragmentDirections.actionMoviesFragmentToDetailMovieFragment(
            movieId = movie.id
        )
        findNavController().navigate(action)
    }
}
