package com.training.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentDetailListBinding
import com.training.movieapp.ui.detail.adapter.MyPagerAdapter
import com.training.movieapp.ui.detail.model.MovieItems
import com.training.movieapp.ui.detail.model.UserItems

class DetailListFragment : Fragment() {

    private lateinit var detailListBinding: FragmentDetailListBinding
    private lateinit var adapter: MyPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailListBinding = FragmentDetailListBinding.inflate(inflater, container, false)
        return detailListBinding.root
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

        detailListBinding.viewPager.adapter = adapter

        val tabTitles = listOf(tabOne, tabTwo)

        TabLayoutMediator(detailListBinding.tab, detailListBinding.viewPager) { tab, position ->
            tab.customView = tabTitles[position]
        }.attach()
    }
}