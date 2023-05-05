package com.training.movieapp.ui.main.adapter.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.common.Constant
import com.training.movieapp.databinding.MovieItemBinding
import com.training.movieapp.domain.model.Movie

class MovieAdapter(
    private val moviesModel: List<Movie>,
    private val onMovieClick: (Movie) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = MovieItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.apply {
            imgMoviePoster.load(Constant.POSTER_BASE_URL + moviesModel[position].posterPath)
            imgMoviePoster.setOnClickListener {
                onMovieClick(moviesModel[position])
            }
        }
    }

    override fun getItemCount() = moviesModel.size
}
