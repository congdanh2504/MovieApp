package com.training.movieapp.ui.main.model

import com.training.movieapp.ui.main.utils.Trending

data class MainSeries(
    val title: String,
    val seriesModels: List<Series>,
    val trending: Trending
)
