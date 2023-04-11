package com.training.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentDetailPersonBinding
import com.training.movieapp.ui.detail.adapter.MyPagerAdapter
import com.training.movieapp.ui.detail.model.MovieItems
import com.training.movieapp.ui.detail.model.UserItems


class DetailPersonFragment : Fragment() {

    private lateinit var detailPersonBinding: FragmentDetailPersonBinding
    private lateinit var adapter: MyPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailPersonBinding = FragmentDetailPersonBinding.inflate(inflater, container, false)
        return detailPersonBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MyPagerAdapter(listOf(MovieItems(listOf()), UserItems(listOf())))
        val tabOne = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabOne.findViewById<TextView>(R.id.number).text = "24"
        tabOne.findViewById<TextView>(R.id.title).text = "Items"
        val tabTwo = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabTwo.findViewById<TextView>(R.id.number).text = "21"
        tabTwo.findViewById<TextView>(R.id.title).text = "Followers"

        detailPersonBinding.viewPager.adapter = adapter

        val tabTitles = listOf(tabOne, tabTwo)

        TabLayoutMediator(detailPersonBinding.tab, detailPersonBinding.viewPager) { tab, position ->
            tab.customView = tabTitles[position]
        }.attach()
    }

}