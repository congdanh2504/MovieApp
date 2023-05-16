package com.training.movieapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.databinding.MovieItemBinding
import com.training.movieapp.domain.model.Series
import com.training.movieapp.ui.main.utils.Images

class SeriesListAdapter(
    private val series: List<Series>,
    private val onSeriesClick: (Int) -> Unit
) :
    RecyclerView.Adapter<SeriesListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindMovie(series: Series) {
            binding.imgMoviePoster.load(
                Images.POSTER_BASE_URL + series.posterPath
            )
            binding.imgMoviePoster.setOnClickListener {
                onSeriesClick(series.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = series.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = series[position]
        holder.bindMovie(series)
    }
}