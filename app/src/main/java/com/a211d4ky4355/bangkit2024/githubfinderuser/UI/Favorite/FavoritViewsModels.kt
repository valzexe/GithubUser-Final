package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Entity.FavoriteUsers
import com.a211d4ky4355.bangkit2024.githubfinderuser.ReposGit.FavoritRepos

class FavoritViewsModels(application: Application) : ViewModel(){
    private val favoriteRepository : FavoritRepos = FavoritRepos(application)

    fun getAllFavorites() : LiveData<List<FavoriteUsers>> = favoriteRepository.getAllFavorite()
}