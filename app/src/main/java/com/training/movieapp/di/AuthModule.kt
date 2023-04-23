package com.training.movieapp.di

import com.training.movieapp.data.repository.AuthRepositoryImpl
import com.training.movieapp.data.usecase.*
import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    abstract fun bindRegisterUseCase(impl: RegisterUseCaseImpl): RegisterUseCase

    @Binds
    abstract fun bindSignOutUseCase(impl: SignOutUseCaseImpl): SignOutUseCase

    @Binds
    abstract fun bindResetPasswordUseCase(impl: ResetPasswordUseCaseImpl): ResetPasswordUseCase

    @Binds
    abstract fun bindSaveUserUseCase(impl: SaveUserUseCaseImpl): SaveUserUseCase

    @Binds
    abstract fun bindReadUserUseCase(impl: ReadUserUseCaseImpl): ReadUserUseCase

    @Binds
    abstract fun bindChangePasswordUseCase(impl: ChangePasswordUseCaseImpl): ChangePasswordUseCase
}