package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.SplashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a211d4ky4355.bangkit2024.githubfinderuser.Perference.SettingsPerference

class SplashViewsModelsFactory(private val pref: SettingsPerference) :

    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewsModels::class.java)) {
            return SplashViewsModels(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}