package com.training.movieapp.data.remote

import com.training.movieapp.domain.model.Credit
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.Series
import com.training.movieapp.domain.model.SeriesDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface SeriesApi {
    @GET("tv/popular")
    suspend fun getSeriesPopular(): PageResponse<SeriesDetail>

    @GET("tv/top_rated")
    suspend fun getSeriesTrending(): PageResponse<SeriesDetail>

    @GET("tv/on_the_air")
    suspend fun getSeriesOnTheAir(): PageResponse<SeriesDetail>

    @GET("tv/airing_today")
    suspend fun getSeriesAiringToday(): PageResponse<SeriesDetail>

    @GET("tv/{tv_id}")
    suspend fun getSeriesDetail(@Path("tv_id") seriesId: Int): SeriesDetail

    @GET("tv/{tv_id}/credits")
    suspend fun getSeriesCredits(@Path("tv_id") seriesId: Int): Credit

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarSeries(@Path("tv_id") seriesId: Int): PageResponse<Series>
}
