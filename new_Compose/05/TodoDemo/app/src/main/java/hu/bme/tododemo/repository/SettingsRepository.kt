package hu.bme.tododemo.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import hu.bme.tododemo.ui.screen.SORTBY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val settingsDataStore: DataStore<Preferences>
) {
    private companion object {
        val KEY_SORT_ORDER = intPreferencesKey(
            name = "sortOrder"
        )
        val KEY_WAS_STARTED = booleanPreferencesKey(
            name = "wasStarted"
        )

    }

    suspend fun setSortOrder(
        sortOrder: Int
    ) {
        settingsDataStore.edit { preferences ->
            preferences[KEY_SORT_ORDER] = sortOrder
        }
    }

    fun getSortOrder(): Flow<Int> {
        return settingsDataStore.data.map { preferences ->
            preferences[KEY_SORT_ORDER] ?: 0
        }
    }

    suspend fun setWasStarted(
        wasStarted: Boolean
    ) {
        settingsDataStore.edit { preferences ->
            preferences[KEY_WAS_STARTED] = wasStarted
        }
    }

    fun getWasStarted(): Flow<Boolean> {
        return settingsDataStore.data.map { preferences ->
            preferences[KEY_WAS_STARTED] ?: false
        }
    }


}