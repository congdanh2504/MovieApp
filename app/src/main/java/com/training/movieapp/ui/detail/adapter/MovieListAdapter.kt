package com.training.movieapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.MovieItem2Binding
import com.training.movieapp.domain.model.MovieCredit
import com.training.movieapp.ui.main.utils.Images

class MovieListAdapter(
    private val movies: List<MovieCredit>,
    private val onMovieClick: (Int) -> Unit
) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MovieItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindMovie(movie: MovieCredit) {
            binding.image.load(
                if (movie.posterPath != null)
                    Images.POSTER_BASE_URL + movie.posterPath
                else R.drawable.noimage
            )
            binding.image.setOnClickListener {
                onMovieClick(movie.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindMovie(movie)
    }
}
