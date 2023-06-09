package com.training.movieapp.ui.main.adapter.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.common.Constant
import com.training.movieapp.databinding.SeriesItemBinding
import com.training.movieapp.domain.model.SeriesDetail

class SeriesAdapter(private val seriesModel: List<SeriesDetail>, private val onSeriesClick : (Int) -> Unit) :
    RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    inner class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SeriesItemBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.series_item, parent, false)
        return SeriesViewHolder(view)
    }

    override fun getItemCount() = seriesModel.size

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.binding.apply {
            imgSeriesPoster.load(Constant.POSTER_BASE_URL + seriesModel[position].posterPath)
            imgSeriesPoster.setOnClickListener {
                onSeriesClick(seriesModel[position].id)
            }
        }
    }
}
