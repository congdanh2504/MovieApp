package com.training.movieapp.data.repository

import com.training.movieapp.common.Result
import com.training.movieapp.data.remote.PeopleApi
import com.training.movieapp.domain.repository.PeopleRepository
import com.training.movieapp.ui.detail.model.PeopleDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val peopleApi: PeopleApi) :
    PeopleRepository {

    override suspend fun searchPeoples(query: String, page: Int) = flow {
        try {
            val peoples = peopleApi.searchPeoples(query, page)
            emit(Result.Success(peoples))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun getPeopleDetail(peopleId: Int): Flow<Result<PeopleDetail>> = flow {
        try {
            val peopleDetailApi = peopleApi.getPeopleDetails(peopleId)
            val movieCredits = peopleApi.getMovieCredits(peopleId)
            emit(
                Result.Success(
                    PeopleDetail(
                        peopleDetailApi.id,
                        peopleDetailApi.birthday,
                        peopleDetailApi.name,
                        peopleDetailApi.biography,
                        peopleDetailApi.placeOfBirth,
                        peopleDetailApi.knownForDepartment,
                        peopleDetailApi.profilePath,
                        movieCredits
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
    override suspend fun getPeoplePopular()= flow{
        try {
            val peoplesPopular = peopleApi.getPeoplePopular()
            emit(Result.Success(peoplesPopular))
        }catch (e:Exception){
            emit(Result.Error(e))
        }
    }
}
