package com.training.movieapp.data.remote

import com.training.movieapp.domain.model.Credit
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.MovieCredits
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.People
import com.training.movieapp.domain.model.PeopleDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi {
    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieDd: Int): Movie

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredit(@Path("movie_id") movieDd: Int): Credit

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieDd: Int): PageResponse<Movie>

    @GET("movie/popular")
    suspend fun getMovieTrending(): PageResponse<Movie>

    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(): PageResponse<Movie>

    @GET("movie/top_rated")
    suspend fun getMovieTopRated(): PageResponse<Movie>

    @GET("movie/upcoming")
    suspend fun getMovieUpcoming(): PageResponse<Movie>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): PageResponse<Movie>

    @GET("search/person")
    suspend fun searchPeoples(
        @Query("query") query: String,
        @Query("page") page: Int
    ): PageResponse<People>

    @GET("person/{person_id}")
    suspend fun getPeopleDetails(@Path("person_id") peopleId: Int): PeopleDetail

    @GET("person/{person_id}/movie_credits")
    suspend fun getMovieCredits(@Path("person_id") peopleId: Int): MovieCredits
}
