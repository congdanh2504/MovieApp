package com.training.movieapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.TrendingItemBinding
import com.training.movieapp.ui.main.model.Movie

class TrendingAdapter(private val moviesModel: List<Movie>) :
    RecyclerView.Adapter<TrendingAdapter.MovieTrendingViewHolder>() {

    inner class MovieTrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = TrendingItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTrendingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.trending_item, parent, false)
        return MovieTrendingViewHolder(view)
    }

    override fun getItemCount() = moviesModel.size

    override fun onBindViewHolder(holder: MovieTrendingViewHolder, position: Int) {
        holder.binding.apply {
            imgMoviePosterTrending.load(moviesModel[position].imageUrl)
        }
    }
}