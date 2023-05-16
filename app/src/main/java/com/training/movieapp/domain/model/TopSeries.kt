package com.training.movieapp.domain.model

data class TopSeries(
    val trendingSeries: PageResponse<SeriesDetail>,
    val airingTodaySeries: PageResponse<SeriesDetail>,
    val onTheAirSeries: PageResponse<SeriesDetail>,
    val popularSeries: PageResponse<SeriesDetail>,
)
