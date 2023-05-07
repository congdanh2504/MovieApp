package com.training.movieapp.data.remote

import com.training.movieapp.domain.model.Credit
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.PageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbApi {
    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieDd: Int): Movie

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredit(@Path("movie_id") movieDd: Int): Credit

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieDd: Int): PageResponse<Movie>

    @GET("movie/popular")
    suspend fun getMovieTrending():PageResponse<Movie>
    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying():PageResponse<Movie>
    @GET("movie/now_playing")
    suspend fun getMovieTopRated():PageResponse<Movie>
    @GET("movie/upcoming")
    suspend fun getMovieUpcoming():PageResponse<Movie>
}