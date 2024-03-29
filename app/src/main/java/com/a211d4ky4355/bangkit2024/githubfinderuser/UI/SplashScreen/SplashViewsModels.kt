package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.SplashScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.a211d4ky4355.bangkit2024.githubfinderuser.Perference.SettingsPerference

class SplashViewsModels(private val pref: SettingsPerference): ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }
}