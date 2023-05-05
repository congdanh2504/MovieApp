package com.training.movieapp.ui.main.adapter.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.common.Constant
import com.training.movieapp.databinding.TrendingMovieItemBinding
import com.training.movieapp.domain.model.Movie

class TrendingMovieAdapter(
    private val moviesModel: List<Movie>,
    private val onMovieClick: (Movie) -> Unit
) :
    RecyclerView.Adapter<TrendingMovieAdapter.MovieTrendingViewHolder>() {

    inner class MovieTrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = TrendingMovieItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTrendingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.trending_movie_item, parent, false)
        return MovieTrendingViewHolder(view)
    }

    override fun getItemCount() = moviesModel.size

    override fun onBindViewHolder(holder: MovieTrendingViewHolder, position: Int) {
        holder.binding.apply {
            imgMoviePosterTrending.load(Constant.POSTER_BASE_URL + moviesModel[position].posterPath)
            imgMoviePosterTrending.setOnClickListener {
                onMovieClick(moviesModel[position])
            }
        }
    }
}
