package hu.bme.aut.datastoredemo.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MySettings @Inject constructor(@ActivityContext private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        private val KEY_EMAIL = stringPreferencesKey("email")
    }

    val getEmail: Flow<String> = context.dataStore.data.map {
            preferences ->
        preferences[KEY_EMAIL] ?: ""
    }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_EMAIL] = email
        }
    }
}