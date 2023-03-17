package com.training.movieapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.training.movieapp.R
import com.training.movieapp.databinding.MovieParentItemBinding
import com.training.movieapp.databinding.TrendingItemBinding
import com.training.movieapp.databinding.TrendingParentItemBinding
import com.training.movieapp.ui.main.adapter.MainMovieAdapter.Const.TRENDING
import com.training.movieapp.ui.main.adapter.MainMovieAdapter.Const.noTRENDING
import com.training.movieapp.ui.main.model.MainMovie
import com.training.movieapp.ui.main.utils.Trending

class MainMovieAdapter(private val collection: List<MainMovie>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    object Const {
        const val TRENDING = 0
        const val noTRENDING = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TRENDING) {
            val view =
                TrendingParentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            TrendingViewHolder(view)
        } else {
            val view =
                MovieParentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CollectionViewHolder(view)
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TRENDING) {
            (holder as TrendingViewHolder).bind(collection[position])
        } else {
            (holder as CollectionViewHolder).bind(collection[position])
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (collection[position].trending == Trending.TRUE) TRENDING else noTRENDING
    }

    override fun getItemCount() = collection.size

    inner class CollectionViewHolder(private val movieParent: MovieParentItemBinding) :
        RecyclerView.ViewHolder(movieParent.root) {
        fun bind(collection: MainMovie) {
            movieParent.tvGenreMovie.text = collection.title
            val movieAdapter = MovieAdapter(collection.movieModels)
            movieParent.rvMovie.adapter = movieAdapter
        }
    }

    inner class TrendingViewHolder(private val trendingParent: TrendingParentItemBinding) :
        RecyclerView.ViewHolder(trendingParent.root) {
        fun bind(collection: MainMovie) {
            trendingParent.tvGenreMovie.text = collection.title
            val trendingAdapter = TrendingAdapter(collection.movieModels)
            trendingParent.rvMovieTrending.adapter = trendingAdapter
        }
    }
}
