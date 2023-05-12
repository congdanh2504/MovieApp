package com.training.movieapp.data.usecase.company

import com.training.movieapp.domain.repository.CompanyRepository
import com.training.movieapp.domain.usecase.company.GetCompanyDetailUseCase
import javax.inject.Inject

class GetCompanyDetailUseCaseImpl @Inject constructor(private val companyRepository: CompanyRepository) : GetCompanyDetailUseCase {
    override suspend fun execute(companyId: Int) = companyRepository.getCompanyDetail(companyId)
}
