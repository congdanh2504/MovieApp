package com.training.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentFollowBinding
import com.training.movieapp.ui.detail.adapter.MyPagerAdapter
import com.training.movieapp.ui.detail.model.UserItems

class FollowFragment : Fragment(R.layout.fragment_follow) {

    private val binding: FragmentFollowBinding by viewBinding(FragmentFollowBinding::bind)
    private lateinit var adapter: MyPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
//        adapter = MyPagerAdapter(listOf(UserItems(listOf()), UserItems(listOf())))
//
//        val tabOne = LayoutInflater.from(requireContext())
//            .inflate(R.layout.custom_tab_title, null) as LinearLayout
//        tabOne.findViewById<TextView>(R.id.number).text = "11"
//        tabOne.findViewById<TextView>(R.id.title).text = "Following"
//        val tabTwo = LayoutInflater.from(requireContext())
//            .inflate(R.layout.custom_tab_title, null) as LinearLayout
//        tabTwo.findViewById<TextView>(R.id.number).text = "3"
//        tabTwo.findViewById<TextView>(R.id.title).text = "Followers"
//
//        binding.viewPager.adapter = adapter
//
//        val tabTitles = listOf(tabOne, tabTwo)
//
//        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
//            tab.customView = tabTitles[position]
//        }.attach()
    }
}
