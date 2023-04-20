package com.training.movieapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.training.movieapp.domain.repository.AuthRepository
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
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
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val userDocument =
                fireStore.collection("users").document(firebaseAuth.currentUser!!.uid)
            val user = userDocument.get().await().toObject(User::class.java)
            emit(Result.Success(user!!))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun register(email: String, username: String, password: String) = flow {
        try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            val userDocument = fireStore.collection("users").document(user!!.uid)
            val userData = hashMapOf(
                "id" to user.uid,
                "email" to email,
                "username" to username,
                "imageURL" to null,
                "bio" to ""
            )
            userDocument.set(userData, SetOptions.merge()).await()
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

}