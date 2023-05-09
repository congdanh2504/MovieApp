package com.training.movieapp.data.remote

import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.Serie
import retrofit2.http.GET

interface TheSeriesDbApi {
    @GET("tv/popular")
    suspend fun getSeriesPopular(): PageResponse<Serie>
    @GET("tv/top_rated")
    suspend fun getSeriesTrending(): PageResponse<Serie>
    @GET("tv/on_the_air")
    suspend fun getSeriesOnTheAir(): PageResponse<Serie>
    @GET("tv/airing_today")
    suspend fun getSeriesAiringToday(): PageResponse<Serie>
}
