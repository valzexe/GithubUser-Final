package com.a211d4ky4355.bangkit2024.githubfinderuser.Perference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsPerference private constructor(private val dataStore: DataStore<Preferences>) {


    private val themeKey = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[themeKey] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[themeKey] = isDarkModeActive
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingsPerference? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingsPerference {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingsPerference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}