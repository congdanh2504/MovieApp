package com.training.movieapp.ui.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.training.movieapp.databinding.MovieItem2Binding

class MovieListAdapter(private val movies: List<String>, private val  context: Context) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MovieItem2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bindMovie(movie: String) {
            Glide.with(context)
                .load(movie)
                .into(binding.image);
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
