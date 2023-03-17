package com.training.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentDetailPersonBinding
import com.training.movieapp.ui.main.NotificationsFragment
import com.training.movieapp.ui.main.SeriesFragment


class DetailPersonFragment : Fragment() {

    private lateinit var detailPersonBinding: FragmentDetailPersonBinding
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailPersonBinding = FragmentDetailPersonBinding.inflate(inflater, container, false)
        return detailPersonBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MainAdapter(requireActivity().supportFragmentManager)
        val tabOne = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabOne.findViewById<TextView>(R.id.number).text = "24"
        tabOne.findViewById<TextView>(R.id.title).text = "Items"
        val tabTwo = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_title, null) as LinearLayout
        tabTwo.findViewById<TextView>(R.id.number).text = "21"
        tabTwo.findViewById<TextView>(R.id.title).text = "Followers"
        adapter.addFragment(MovieListFragment())
        adapter.addFragment(UserListFragment())
        detailPersonBinding.viewPager.adapter = adapter
        detailPersonBinding.tab.setupWithViewPager(detailPersonBinding.viewPager)
        detailPersonBinding.tab.getTabAt(0)?.customView = tabOne
        detailPersonBinding.tab.getTabAt(1)?.customView = tabTwo
    }

    private class MainAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

        private val fragments: ArrayList<Fragment> = arrayListOf()
        fun addFragment(fragment: Fragment) {
            fragments.add(fragment)
        }

        override fun getCount(): Int = fragments.size

        override fun getItem(position: Int): Fragment = fragments[position]
    }

}