package com.training.movieapp.ui.main.model

import com.training.movieapp.domain.model.SeriesDetail
import com.training.movieapp.ui.main.utils.Trending

data class MainSeries(
    val title: String,
    val seriesModels: List<SeriesDetail>,
    val trending: Trending
)
