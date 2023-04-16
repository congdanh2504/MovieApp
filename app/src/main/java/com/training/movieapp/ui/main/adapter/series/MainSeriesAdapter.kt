package com.training.movieapp.ui.main.adapter.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.training.movieapp.databinding.SeriesParentItemBinding
import com.training.movieapp.databinding.TrendingSeriesParentItemBinding
import com.training.movieapp.ui.main.adapter.series.MainSeriesAdapter.Const.TRENDING
import com.training.movieapp.ui.main.adapter.series.MainSeriesAdapter.Const.noTRENDING
import com.training.movieapp.ui.main.model.MainSeries
import com.training.movieapp.ui.main.utils.Trending

class MainSeriesAdapter(private val collectionSeries: List<MainSeries>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    object Const {
        const val TRENDING = 0
        const val noTRENDING = 1
    }

    inner class CollectionSeriesViewHolder(private val seriesParent: SeriesParentItemBinding) :
        RecyclerView.ViewHolder(seriesParent.root) {
        fun bind(collectionSeries: MainSeries) {
            seriesParent.tvGenreSeries.text = collectionSeries.title
            val seriesAdapter = SeriesAdapter(collectionSeries.seriesModels)
            seriesParent.rvSeries.adapter = seriesAdapter
        }
    }

    inner class TrendingSeriesViewHolder(private val trendingSeriesParent: TrendingSeriesParentItemBinding) :
        RecyclerView.ViewHolder(trendingSeriesParent.root) {
        fun bind(collectionSeries: MainSeries) {
            trendingSeriesParent.tvGenreSeries.text = collectionSeries.title
            val trendingSeriesAdapter = TrendingSeriesAdapter(collectionSeries.seriesModels)
            trendingSeriesParent.rvSeriesTrending.adapter = trendingSeriesAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MainSeriesAdapter.Const.TRENDING) {
            val view =
                TrendingSeriesParentItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            TrendingSeriesViewHolder(view)
        } else {
            val view =
                SeriesParentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CollectionSeriesViewHolder(view)
        }
    }

    override fun getItemCount() = collectionSeries.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TRENDING) {
            (holder as TrendingSeriesViewHolder).bind(collectionSeries[position])
        } else {
            (holder as CollectionSeriesViewHolder).bind(collectionSeries[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (collectionSeries[position].trending == Trending.TRUE) TRENDING else noTRENDING
    }
}
