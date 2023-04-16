package com.training.movieapp.ui.main.adapter.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.TrendingSeriesItemBinding
import com.training.movieapp.ui.main.model.Series

class TrendingSeriesAdapter(private val seriesModel: List<Series>) :
    RecyclerView.Adapter<TrendingSeriesAdapter.TrendingSeriesViewHolder>() {
    inner class TrendingSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = TrendingSeriesItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingSeriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.trending_series_item, parent, false)
        return TrendingSeriesViewHolder(view)
    }

    override fun getItemCount() = seriesModel.size

    override fun onBindViewHolder(holder: TrendingSeriesViewHolder, position: Int) {
        holder.binding.apply {
            imgSeriesPosterTrending.load(seriesModel[position].imageUrl)
        }
    }
}
