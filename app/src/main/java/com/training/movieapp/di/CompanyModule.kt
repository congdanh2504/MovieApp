package com.training.movieapp.di

import com.training.movieapp.data.repository.CompanyRepositoryImpl
import com.training.movieapp.data.usecase.company.GetCompanyDetailUseCaseImpl
import com.training.movieapp.domain.repository.CompanyRepository
import com.training.movieapp.domain.usecase.company.GetCompanyDetailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CompanyModule {

    @Binds
    fun bindCompanyRepository(impl: CompanyRepositoryImpl): CompanyRepository

    @Binds
    fun bindGetCompanyDetailUseCase(impl: GetCompanyDetailUseCaseImpl): GetCompanyDetailUseCase
}
