package com.training.movieapp.data.repository

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) :
    AuthRepository {
    override suspend fun login(email: String, password: String) = flow {
        try {
            val currentUser = firebaseAuth.signInWithEmailAndPassword(email, password).await().user
                ?: throw NullPointerException("User is null")
            val user = fireStore.collection("users").document(currentUser.uid).get().await()
                .toObject(User::class.java) ?: throw NullPointerException("User is null")
            emit(Result.Success(user))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun register(email: String, username: String, password: String) = flow {
        try {
            val user = firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
                ?: throw NullPointerException("User is null")
            val userData = mapOf(
                "id" to user.uid,
                "email" to email,
                "username" to username,
                "imageURL" to null,
                "bio" to ""
            )
            fireStore.collection("users").document(user.uid).set(userData, SetOptions.merge())
                .await()
            emit(Result.Success(Unit))
            firebaseAuth.signOut()
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun signOut() = flow {
        try {
            firebaseAuth.signOut()
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun resetPassword(email: String) = flow {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun changePassword(
        email: String,
        currentPassword: String,
        newPassword: String
    ) = flow {
        try {
            val credential = EmailAuthProvider.getCredential(email, currentPassword)
            val currentUser = firebaseAuth.currentUser ?: throw NullPointerException("User is null")
            currentUser.reauthenticate(credential).await()
            currentUser.updatePassword(newPassword)
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun changeEmail(
        email: String,
        currentPassword: String,
        newEmail: String
    ) = flow {
        try {
            val credential = EmailAuthProvider.getCredential(email, currentPassword)
            val currentUser = firebaseAuth.currentUser ?: throw NullPointerException("User is null")
            currentUser.reauthenticate(credential).await()
            currentUser.updateEmail(newEmail).await()
            val userDocument = fireStore.collection("users").document(currentUser.uid)
            userDocument.update("email", newEmail).await()
            val user =
                userDocument.get().await().toObject(User::class.java) ?: throw NullPointerException(
                    "User is null"
                )
            emit(Result.Success(user))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
