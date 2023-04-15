package com.training.movieapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.training.movieapp.data.repository.AuthRepositoryImpl
import com.training.movieapp.data.usecase.AuthUseCaseImpl
import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.domain.usecase.AuthUseCase
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
    fun provideAuthUseCase(repository: AuthRepository): AuthUseCase {
        return AuthUseCaseImpl(repository)
    }
}