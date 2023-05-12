package com.training.movieapp.data.remote

import com.training.movieapp.domain.model.MovieCredits
import com.training.movieapp.domain.model.PageResponse
import com.training.movieapp.domain.model.People
import com.training.movieapp.domain.model.PeopleDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleApi {

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
