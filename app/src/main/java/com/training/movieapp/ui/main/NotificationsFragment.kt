package com.training.movieapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentNotificationsBinding
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.main.adapter.notifications.NotificationsAdapter
import com.training.movieapp.ui.main.viewmodel.NotificationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class NotificationsFragment : Fragment(R.layout.fragment_notifications) {
    private val binding: FragmentNotificationsBinding by viewBinding(FragmentNotificationsBinding::bind)
    private val notificationsViewModel: NotificationsViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                notificationsViewModel.usersState.collect { state ->
                    when (state) {
                        is DataState.Success -> {
                            setUsers(state.data)
                            Log.d("notification",state.data.toString())
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
        }    }

    private fun setUsers(data: List<User>) {
        binding.apply {
            rvNotifications.adapter = NotificationsAdapter(data)
        }
    }

}
