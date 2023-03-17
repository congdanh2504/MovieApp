package com.training.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentMovieListBinding
import com.training.movieapp.ui.detail.adapter.MovieListAdapter


class MovieListFragment : Fragment() {

    private lateinit var movieListBinding: FragmentMovieListBinding
    private lateinit var adapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        movieListBinding = FragmentMovieListBinding.inflate(inflater, container, false)
        return movieListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movies: List<String> = listOf(
            "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
            "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
            "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
            "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
            "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
            "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
            "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
            "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
            "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
            "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg"
        )
        adapter = MovieListAdapter(movies, requireContext())
        movieListBinding.movieRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        movieListBinding.movieRecycler.adapter = adapter
    }
}