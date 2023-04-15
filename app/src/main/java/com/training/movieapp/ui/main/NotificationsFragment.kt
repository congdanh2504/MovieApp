package com.training.movieapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentNotificationsBinding
import com.training.movieapp.ui.main.adapter.notifications.NotificationsAdapter
import com.training.movieapp.ui.main.utils.SampleData

class NotificationsFragment : Fragment() {
    private lateinit var binding : FragmentNotificationsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationsBinding.inflate(inflater,container,false)
        binding.apply {
            rvNotifications.adapter = NotificationsAdapter(SampleData.User)
        }
        return binding.root
    }
}
