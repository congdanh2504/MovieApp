package com.training.movieapp.data.repository

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.google.gson.Gson
import com.training.movieapp.domain.model.User
import com.training.movieapp.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(context: Context) :
    DataStoreRepository {

    companion object {
        const val APP_PREFERENCES = "app_preferences"
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(APP_PREFERENCES)

    private object PreferenceKeys {
        val user = preferencesKey<String>("user")
    }

    override suspend fun saveUser(user: User) {
        dataStore.edit { preference ->
            val jsonUser = Gson().toJson(user)
            preference[PreferenceKeys.user] = jsonUser
        }
    }

    override suspend fun readUser(): Flow<User> =
        dataStore.data
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