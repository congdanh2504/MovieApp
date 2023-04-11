package com.training.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentFollowBinding
import com.training.movieapp.ui.detail.adapter.MyPagerAdapter
import com.training.movieapp.ui.detail.model.UserItems

class FollowFragment : Fragment() {

    private lateinit var followBinding: FragmentFollowBinding
    private lateinit var adapter: MyPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        followBinding = FragmentFollowBinding.inflate(inflater, container, false)
        return followBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followBinding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        adapter = MyPagerAdapter(listOf(UserItems(listOf()), UserItems(listOf())))

        val tabOne = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabOne.findViewById<TextView>(R.id.number).text = "11"
        tabOne.findViewById<TextView>(R.id.title).text = "Following"
        val tabTwo = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabTwo.findViewById<TextView>(R.id.number).text = "3"
        tabTwo.findViewById<TextView>(R.id.title).text = "Followers"

        followBinding.viewPager.adapter = adapter

        val tabTitles = listOf(tabOne, tabTwo)

        TabLayoutMediator(followBinding.tab, followBinding.viewPager) { tab, position ->
            tab.customView = tabTitles[position]
        }.attach()
    }
}