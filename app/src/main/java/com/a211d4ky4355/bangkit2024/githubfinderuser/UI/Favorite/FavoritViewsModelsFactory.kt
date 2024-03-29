package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Favorite

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoritViewsModelsFactory (private val application: Application) :

    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritViewsModels::class.java)) {
            return FavoritViewsModels(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: FavoritViewsModelsFactory? = null
        fun getInstance(application: Application): FavoritViewsModelsFactory =
            instance ?: synchronized(this) {
                instance ?: FavoritViewsModelsFactory(application)
            }.also { instance = it }
    }
}