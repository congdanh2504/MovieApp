package com.training.movieapp.data.remote

import com.training.movieapp.domain.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbApi {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieDd: Int): MovieDetail
}