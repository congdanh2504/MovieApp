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
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentExploreBinding
import com.training.movieapp.domain.model.People
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.main.adapter.explore.ExploreAdapter
import com.training.movieapp.ui.main.utils.SampleData
import com.training.movieapp.ui.main.viewmodel.ExploreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {
    private val exploreViewModel: ExploreViewModel by viewModels()
    private val binding: FragmentExploreBinding by viewBinding(FragmentExploreBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exploreViewModel.getPeoplePopular()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                exploreViewModel.peoplePopularState.collect { state ->
                    when (state) {
                        is DataState.Success -> {
                            setPeople(state.data.results)
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

    private fun setPeople(data: List<People>) {
        SampleData.Performer = data
        binding.apply {
            rvMainExplore.adapter = ExploreAdapter(SampleData.listView)
        }
    }
}
