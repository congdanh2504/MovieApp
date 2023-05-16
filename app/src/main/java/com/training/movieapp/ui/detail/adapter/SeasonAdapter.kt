package com.training.movieapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.databinding.SeasonItemBinding
import com.training.movieapp.domain.model.Season
import com.training.movieapp.ui.main.utils.Images

class SeasonAdapter(
    private val seasons: List<Season>,
) :
    RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: SeasonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindMovie(season: Season) {
            binding.imageViewPosterImage.load(
                Images.POSTER_BASE_URL + season.posterPath
            )
            binding.textViewName.text = season.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SeasonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = seasons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val season = seasons[position]
        holder.bindMovie(season)
    }
}