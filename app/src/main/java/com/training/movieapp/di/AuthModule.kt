package com.training.movieapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.training.movieapp.data.repository.AuthRepositoryImpl
import com.training.movieapp.data.usecase.*
import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFireStore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        fireStore: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(auth, fireStore)

    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCaseImpl(repository)
    }

    @Provides
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCaseImpl(repository)
    }

    @Provides
    fun provideSignOutUseCase(repository: AuthRepository): SignOutUseCase {
        return SignOutUseCaseImpl(repository)
    }

    @Provides
    fun provideResetPasswordUseCase(repository: AuthRepository): ResetPasswordUseCase {
        return ResetPasswordUseCaseImpl(repository)
    }
}