package com.training.movieapp.di

import com.training.movieapp.data.repository.UserRepositoryImpl
import com.training.movieapp.data.usecase.user.CheckUserLoggedInUseCaseImpl
import com.training.movieapp.data.usecase.user.UpdateProfileUseCaseImpl
import com.training.movieapp.domain.repository.UserRepository
import com.training.movieapp.domain.usecase.user.CheckUserLoggedInUseCase
import com.training.movieapp.domain.usecase.user.UpdateProfileUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserBindModule {

    @Binds
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindUpdateProfileUseCase(impl: UpdateProfileUseCaseImpl): UpdateProfileUseCase

    @Binds
    abstract fun bindCheckUserIsLoggedUseCase(impl: CheckUserLoggedInUseCaseImpl): CheckUserLoggedInUseCase
}