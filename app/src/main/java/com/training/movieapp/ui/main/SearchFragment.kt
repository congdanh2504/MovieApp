package com.training.movieapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentSearchBinding
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.People
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.main.adapter.movie.PaginationMovieAdapter
import com.training.movieapp.ui.main.adapter.movie.PaginationPeopleAdapter
import com.training.movieapp.ui.main.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var movieAdapter: PaginationMovieAdapter
    private lateinit var peopleAdapter: PaginationPeopleAdapter
    private var queryText: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
        initRecyclerView()
        initObservers()
    }

    private fun initActions() {
        var searchTimer: Timer? = null
        binding.apply {
            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchTimer?.cancel()
                    searchTimer = Timer()
                    searchTimer?.schedule(
                        object : TimerTask() {
                            override fun run() {
                                queryText = newText.toString()
                                searchViewModel.searchPeoples(queryText)
                                searchViewModel.searchMovies(queryText)
                            }
                        },
                        600
                    )
                    return true
                }
            })
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerViewMovies.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerViewPeoples.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            movieAdapter = PaginationMovieAdapter(onMovieClick, onLoadMoreMovies)
            peopleAdapter = PaginationPeopleAdapter(onPeopleClick, onLoadMorePeoples)
            recyclerViewMovies.adapter = movieAdapter
            recyclerViewPeoples.adapter = peopleAdapter
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    searchViewModel.searchPeoplesState.collect { state ->
                        when (state) {
                            is DataState.Success -> {
                                if (state.data.page == 1) {
                                    peopleAdapter.totalResult = state.data.totalResults
                                    setPeoples(state.data)
                                    checkNull()
                                } else {
                                    peopleAdapter.addPeoples(state.data.results)
                                }
                            }

                            is DataState.Error -> Toast.makeText(
                                requireContext(),
                                state.message,
                                Toast.LENGTH_LONG
                            ).show()

                            else -> {}
                        }
                    }
                }
                launch {
                    searchViewModel.searchMoviesState.collect { state ->
                        when (state) {
                            is DataState.Success -> {
                                if (state.data.page == 1) {
                                    movieAdapter.totalResult = state.data.totalResults
                                    setMovies(state.data)
                                    checkNull()
                                } else {
                                    movieAdapter.addMovies(state.data.results)
                                }
                            }

                            is DataState.Error -> Toast.makeText(
                                requireContext(),
                                state.message,
                                Toast.LENGTH_LONG
                            ).show()

                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private val onMovieClick: (movieId: Int) -> Unit = { movieId ->
        val action =
            SearchFragmentDirections.actionSearchFragmentToDetailMovieFragment(movieId)
        findNavController().navigate(action)
    }

    private val onPeopleClick: (people: People) -> Unit = { people ->
        val action =
            SearchFragmentDirections.actionSearchFragmentToDetailPersonFragment(peopleId = people.id)
        findNavController().navigate(action)
    }

    private val onLoadMoreMovies: (page: Int) -> Unit = { page ->
        searchViewModel.searchMovies(queryText, page = page)
    }

    private val onLoadMorePeoples: (page: Int) -> Unit = { page ->
        searchViewModel.searchPeoples(queryText, page = page)
    }

    private fun setPeoples(peoplesResponse: PageResponse<People>) {
        if (peoplesResponse.results.isNotEmpty()) {
            setHavePeoples(peoplesResponse)
        } else {
            setNoPeoples()
        }
    }

    private fun setMovies(moviesResponse: PageResponse<Movie>) {
        if (moviesResponse.results.isNotEmpty()) {
            setHaveMovies(moviesResponse)
        } else {
            setNoMovies()
        }
    }

    private fun setHavePeoples(peoplesResponse: PageResponse<People>) {
        binding.linearLayoutPeoples.isVisible = true
        binding.recyclerViewPeoples.isVisible = true
        binding.constraintLayoutNoData.isVisible = false
        binding.linearLayoutHaveData.isVisible = true
        peopleAdapter.setPeoples(peoplesResponse.results)
        binding.textViewPeopleCount.text = peoplesResponse.totalResults.toString()
    }

    private fun setNoPeoples() {
        binding.linearLayoutPeoples.isVisible = false
        binding.recyclerViewPeoples.isVisible = false
    }

    private fun setHaveMovies(moviesResponse: PageResponse<Movie>) {
        binding.linearLayoutMovies.isVisible = true
        binding.recyclerViewMovies.isVisible = true
        binding.constraintLayoutNoData.isVisible = false
        binding.linearLayoutHaveData.isVisible = true
        movieAdapter.setMovies(moviesResponse.results)
        binding.textViewMovieCount.text = moviesResponse.totalResults.toString()
    }

    private fun setNoMovies() {
        binding.linearLayoutMovies.isVisible = false
        binding.recyclerViewMovies.isVisible = false
    }

    private fun checkNull() {
        try {
            val movies = searchViewModel.searchMoviesState.value as DataState.Success
            val peoples = searchViewModel.searchPeoplesState.value as DataState.Success
            if (movies.data.results.isEmpty() and peoples.data.results.isEmpty()) {
                binding.constraintLayoutNoData.isVisible = true
                binding.textViewTitle.text = "No Results"
                binding.textViewDescription.text = "For \"$queryText\""
            } else {
                binding.constraintLayoutNoData.isVisible = false
            }
        } catch (e: Exception) {
            // do nothing
        }
    }
}
