package com.training.movieapp.domain.repository

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.CompanyDetail
import kotlinx.coroutines.flow.Flow

interface CompanyRepository {

    suspend fun getCompanyDetail(companyId: Int): Flow<Result<CompanyDetail>>
}