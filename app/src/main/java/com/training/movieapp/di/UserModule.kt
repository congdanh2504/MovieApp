package com.training.movieapp.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.training.movieapp.data.repository.DataStoreRepositoryImpl
import com.training.movieapp.data.repository.UserRepositoryImpl
import com.training.movieapp.data.usecase.UpdateProfileUseCaseImpl
import com.training.movieapp.domain.repository.DataStoreRepository
import com.training.movieapp.domain.repository.UserRepository
import com.training.movieapp.domain.usecase.UpdateProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(context)

    @Provides
    fun provideUserRepository(
        auth: FirebaseAuth,
        fireStore: FirebaseFirestore,
        storage: FirebaseStorage
    ): UserRepository = UserRepositoryImpl(auth, fireStore, storage)

    @Provides
    fun provideUpdateProfileUseCase(repository: UserRepository): UpdateProfileUseCase {
        return UpdateProfileUseCaseImpl(repository)
    }
}