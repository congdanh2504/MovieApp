package com.training.movieapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.common.Result
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) :
    AuthRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun register(email: String, username: String, password: String): Result<Unit> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            val userDocument = fireStore.collection("users").document(user!!.uid)
            val userData = hashMapOf(
                "email" to email,
                "username" to username
            )
            userDocument.set(userData, SetOptions.merge()).await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return try {
            firebaseAuth.signOut()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun resetPassword(email: String): Result<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}