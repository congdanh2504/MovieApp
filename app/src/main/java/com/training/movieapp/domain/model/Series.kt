package com.training.movieapp.domain.model

data class Series(
    val trendingSeries: PageResponse<Serie>,
    val airingTodaySeries: PageResponse<Serie>,
    val onTheAirSeries: PageResponse<Serie>,
    val popularSeries: PageResponse<Serie>,
)
