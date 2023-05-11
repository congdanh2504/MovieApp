package com.training.movieapp.data.repository

import com.training.movieapp.common.Result
import com.training.movieapp.data.remote.CompanyApi
import com.training.movieapp.domain.repository.CompanyRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(private val companyApi: CompanyApi) :
    CompanyRepository {
    override suspend fun getCompanyDetail(companyId: Int) = flow {
        try {
            val companyDetail = companyApi.getCompanyDetail(companyId)
            emit(Result.Success(companyDetail))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}