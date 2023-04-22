package com.training.movieapp.di

import com.training.movieapp.data.repository.UserRepositoryImpl
import com.training.movieapp.data.usecase.UpdateProfileUseCaseImpl
import com.training.movieapp.domain.repository.UserRepository
import com.training.movieapp.domain.usecase.UpdateProfileUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserBindModule {

    @Binds
    abstract fun provideUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun provideUpdateProfileUseCase(impl: UpdateProfileUseCaseImpl): UpdateProfileUseCase
}