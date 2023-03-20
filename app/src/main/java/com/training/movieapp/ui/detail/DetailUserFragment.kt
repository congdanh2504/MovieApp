package com.training.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentDetailUserBinding
import com.training.movieapp.ui.detail.adapter.MainAdapter

class DetailUserFragment : Fragment() {

    private lateinit var detailUserBinding: FragmentDetailUserBinding
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailUserBinding = FragmentDetailUserBinding.inflate(inflater, container, false)
        return detailUserBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MainAdapter(requireActivity().supportFragmentManager)
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
        adapter.addFragment(MovieListFragment())
        adapter.addFragment(MovieListFragment())
        adapter.addFragment(MovieListFragment())
        adapter.addFragment(UserListFragment())
        detailUserBinding.viewPager.adapter = adapter
        detailUserBinding.tab.setupWithViewPager(detailUserBinding.viewPager)
        detailUserBinding.tab.getTabAt(0)?.customView = tabOne
        detailUserBinding.tab.getTabAt(1)?.customView = tabTwo
        detailUserBinding.tab.getTabAt(2)?.customView = tabThree
        detailUserBinding.tab.getTabAt(3)?.customView = tabFour
    }
}