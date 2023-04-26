package com.training.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.training.movieapp.R
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentDetailMovieBinding
import com.training.movieapp.domain.model.Cast
import com.training.movieapp.domain.model.Crew
import com.training.movieapp.ui.detail.adapter.CastAndCrewAdapter
import com.training.movieapp.ui.detail.adapter.MyPagerAdapter
import com.training.movieapp.ui.main.adapter.movie.MovieAdapter
import com.training.movieapp.ui.main.utils.SampleData

class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    private val binding: FragmentDetailMovieBinding by viewBinding(FragmentDetailMovieBinding::bind)
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var castAndCrewAdapter: CastAndCrewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter(SampleData.Movies)
        binding.reyclerViewMovie.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.reyclerViewMovie.adapter = movieAdapter
        initTabLayout()
    }

    private fun initTabLayout() {
        val casts = mutableListOf<Cast>()
        val crews = mutableListOf<Crew>()
        for (i in 1..10) {
            casts.add(Cast(1, "/ln8nIx6UjxpMLVQlStCJpx6fyL7.jpg", "John", "Cong Danh"))
            crews.add(Crew(1, "/ln8nIx6UjxpMLVQlStCJpx6fyL7.jpg", "Director", "Cong MInh"))
        }

        castAndCrewAdapter = CastAndCrewAdapter(casts, crews)
        val tabTitles = listOf(getTab(casts.size, "Cast"), getTab(crews.size, "Crew"))

        binding.viewPager.adapter = castAndCrewAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.customView = tabTitles[position]
        }.attach()
    }

    private fun getTab(number: Int, title: String): LinearLayout {
        val tab = LayoutInflater.from(requireContext())
            .inflate(R.layout.cast_tab_title, null) as LinearLayout
        tab.findViewById<TextView>(R.id.number).text = number.toString()
        tab.findViewById<TextView>(R.id.title).text = title
        return tab
    }

}