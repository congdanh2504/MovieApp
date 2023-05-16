package com.training.movieapp.ui.detail.model

import com.training.movieapp.domain.model.Credit
import com.training.movieapp.domain.model.Series
import com.training.movieapp.domain.model.SeriesDetail

data class SeriesDetailView(
    val seriesDetail: SeriesDetail,
    val credits: Credit,
    val similarSeries: List<Series>
)