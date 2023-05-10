package com.training.movieapp.di

import com.training.movieapp.data.repository.PeopleRepositoryImpl
import com.training.movieapp.data.usecase.people.GetPeopleDetailUseCaseImpl
import com.training.movieapp.data.usecase.people.SearchPeoplesUseCaseImpl
import com.training.movieapp.domain.repository.PeopleRepository
import com.training.movieapp.domain.usecase.people.GetPeopleDetailUseCase
import com.training.movieapp.domain.usecase.people.SearchPeoplesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PeopleModule {

    @Binds
    abstract fun bindPeopleRepository(impl: PeopleRepositoryImpl): PeopleRepository

    @Binds
    abstract fun bindSearchPeoplesUseCase(impl: SearchPeoplesUseCaseImpl): SearchPeoplesUseCase

    @Binds
    abstract fun bindGetPeopleDetailUseCase(impl: GetPeopleDetailUseCaseImpl): GetPeopleDetailUseCase
}