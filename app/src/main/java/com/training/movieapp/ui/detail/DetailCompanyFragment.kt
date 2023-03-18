package com.training.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentDetailCompanyBinding
import com.training.movieapp.ui.detail.adapter.MainAdapter

class DetailCompanyFragment : Fragment() {

    private lateinit var detailCompanyBinding: FragmentDetailCompanyBinding
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailCompanyBinding = FragmentDetailCompanyBinding.inflate(inflater, container, false)
        return detailCompanyBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MainAdapter(requireActivity().supportFragmentManager)
        val tabOne = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabOne.findViewById<TextView>(R.id.number).text = "88"
        tabOne.findViewById<TextView>(R.id.title).text = "Items"
        val tabTwo = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabTwo.findViewById<TextView>(R.id.number).text = "3"
        tabTwo.findViewById<TextView>(R.id.title).text = "Followers"
        adapter.addFragment(MovieListFragment())
        adapter.addFragment(UserListFragment())
        detailCompanyBinding.viewPager.adapter = adapter
        detailCompanyBinding.tab.setupWithViewPager(detailCompanyBinding.viewPager)
        detailCompanyBinding.tab.getTabAt(0)?.customView = tabOne
        detailCompanyBinding.tab.getTabAt(1)?.customView = tabTwo
    }

}