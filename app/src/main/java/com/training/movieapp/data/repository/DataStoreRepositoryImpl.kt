package com.training.movieapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.training.movieapp.common.Result
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(private val context: Context) :
    DataStoreRepository {

    companion object {
        private const val APP_PREFERENCES = "app_preferences"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            APP_PREFERENCES
        )
    }

    private object PreferenceKeys {
        val user = stringPreferencesKey("user")
    }

    override suspend fun saveUser(user: User) {
        context.dataStore.edit { preference ->
            val jsonUser = Gson().toJson(user)
            preference[PreferenceKeys.user] = jsonUser
        }
    }

    override suspend fun readUser() = flow {
        try {
            val user = context.dataStore.data.map { preference ->
                val userJson = preference[PreferenceKeys.user] ?: ""
                Gson().fromJson(userJson, User::class.java)
            }.firstOrNull()

            if (user == null) {
                emit(Result.Error(Exception("User not found")))
            } else {
                emit(Result.Success(user))
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
