package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.a211d4ky4355.bangkit2024.githubfinderuser.Perference.SettingsPerference
import kotlinx.coroutines.launch

class SettingsModeViewsModels(private val pref: SettingsPerference): ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}