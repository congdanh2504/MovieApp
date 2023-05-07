package com.training.movieapp.data.repository

import com.training.movieapp.common.Result
import com.training.movieapp.data.remote.TheMovieDbApi
import com.training.movieapp.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val theMovieDbApi: TheMovieDbApi) :
    PeopleRepository {

    override suspend fun searchPeoples(query: String, page: Int) = flow {
        try {
            val peoples = theMovieDbApi.searchPeoples(query, page)
            emit(Result.Success(peoples))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

}