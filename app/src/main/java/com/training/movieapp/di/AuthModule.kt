package com.training.movieapp.di

import com.training.movieapp.data.repository.AuthRepositoryImpl
import com.training.movieapp.data.usecase.*
import com.training.movieapp.data.usecase.auth.ChangeEmailUseCaseImpl
import com.training.movieapp.data.usecase.auth.ChangePasswordUseCaseImpl
import com.training.movieapp.data.usecase.auth.LoginUseCaseImpl
import com.training.movieapp.data.usecase.auth.RegisterUseCaseImpl
import com.training.movieapp.data.usecase.auth.ResetPasswordUseCaseImpl
import com.training.movieapp.data.usecase.auth.SignOutUseCaseImpl
import com.training.movieapp.data.usecase.datastore.ReadUserUseCaseImpl
import com.training.movieapp.data.usecase.datastore.SaveUserUseCaseImpl
import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.*
import com.training.movieapp.domain.usecase.auth.ChangeEmailUseCase
import com.training.movieapp.domain.usecase.auth.ChangePasswordUseCase
import com.training.movieapp.domain.usecase.auth.LoginUseCase
import com.training.movieapp.domain.usecase.auth.RegisterUseCase
import com.training.movieapp.domain.usecase.auth.ResetPasswordUseCase
import com.training.movieapp.domain.usecase.auth.SignOutUseCase
import com.training.movieapp.domain.usecase.datastore.ReadUserUseCase
import com.training.movieapp.domain.usecase.datastore.SaveUserUseCase
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

    @Binds
    abstract fun bindChangeEmailUseCase(impl: ChangeEmailUseCaseImpl): ChangeEmailUseCase
}