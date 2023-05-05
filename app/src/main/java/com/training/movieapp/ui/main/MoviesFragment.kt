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
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.main.adapter.movie.MainMovieAdapter
import com.training.movieapp.ui.main.model.MainMovie
import com.training.movieapp.ui.main.utils.Trending
import com.training.movieapp.ui.main.viewmodel.MainViewModel
import com.training.movieapp.ui.main.viewmodel.TrendingMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private val trendingMovieViewModel: TrendingMovieViewModel by viewModels()
    private val binding: FragmentMoviesBinding by viewBinding(FragmentMoviesBinding::bind)
    private lateinit var dialog: LoadingDialog
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        dialog = LoadingDialog(requireContext())
        mainViewModel.readUser()
        trendingMovieViewModel.getTrendingMovie()
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                trendingMovieViewModel.movieTrendingState.collect { state ->
                    when (state) {
                        is DataState.Idle -> {
//                            dialog.dismiss()
                        }

                        is DataState.Loading -> {
//                            dialog.show()
                        }

                        is DataState.Success -> {
//                            dialog.dismiss()
                            setMovieTrending(state.data)
                        }

                        is DataState.Error -> {
//                            dialog.dismiss()
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

    private fun setMovieTrending(pageResponse: PageResponse<Movie>) {
        setListMovie(pageResponse.results)
    }

    private fun setListMovie(movies: List<Movie>) {
        val collections = listOf(
            MainMovie("Trending Now", movies, Trending.TRUE),
            MainMovie("Popular on Letterboxd", movies, Trending.FALSE),
            MainMovie("In Theaters", movies, Trending.FALSE),
            MainMovie("Coming Soon", movies, Trending.FALSE),
            MainMovie("Most Popular", movies, Trending.FALSE),
            MainMovie("Most Anticipated", movies, Trending.FALSE),
        )
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
