package com.training.movieapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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

    override suspend fun readUser(): Flow<User> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preference ->
                val user = preference[PreferenceKeys.user] ?: ""
                Gson().fromJson(user, User::class.java)
            }
}
