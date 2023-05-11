package com.training.movieapp.domain.usecase.company

import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.CompanyDetail
import kotlinx.coroutines.flow.Flow

interface GetCompanyDetailUseCase {
    suspend fun execute(companyId: Int): Flow<Result<CompanyDetail>>
}