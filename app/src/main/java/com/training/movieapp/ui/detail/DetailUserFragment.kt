package com.training.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentDetailUserBinding
import com.training.movieapp.ui.detail.adapter.MyPagerAdapter
import com.training.movieapp.ui.detail.model.MovieItems
import com.training.movieapp.ui.detail.model.UserItems

class DetailUserFragment : Fragment() {

    private lateinit var detailUserBinding: FragmentDetailUserBinding
    private lateinit var adapter: MyPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailUserBinding = FragmentDetailUserBinding.inflate(inflater, container, false)
        return detailUserBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MyPagerAdapter(listOf(MovieItems(listOf()), MovieItems(listOf()),MovieItems(listOf()), UserItems(listOf())))
        val tabOne = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabOne.findViewById<TextView>(R.id.number).text = "24"
        tabOne.findViewById<TextView>(R.id.title).text = "Watch List"
        val tabTwo = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabTwo.findViewById<TextView>(R.id.number).text = "185"
        tabTwo.findViewById<TextView>(R.id.title).text = "Watched List"
        val tabThree = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabThree.findViewById<TextView>(R.id.number).text = "52"
        tabThree.findViewById<TextView>(R.id.title).text = "Watching List"
        val tabFour = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabFour.findViewById<TextView>(R.id.number).text = "21"
        tabFour.findViewById<TextView>(R.id.title).text = "User Reviews"
        detailUserBinding.viewPager.adapter = adapter

        val tabTitles = listOf(tabOne, tabTwo, tabThree, tabFour)

        TabLayoutMediator(detailUserBinding.tab, detailUserBinding.viewPager) { tab, position ->
            tab.customView = tabTitles[position]
        }.attach()
        detailUserBinding.follow.setOnClickListener {
//            findNavController().navigate(R.id.action_detailUserFragment_to_followFragment)
        }
    }

}