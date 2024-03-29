package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.ResponseUser
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Retrofit.ApiConfig
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Entity.FavoriteUsers
import com.a211d4ky4355.bangkit2024.githubfinderuser.ReposGit.FavoritRepos
import com.a211d4ky4355.bangkit2024.githubfinderuser.ReposGit.GithubRepos

class DetailViewsModel(application: Application) :ViewModel(){

    private val mFavoriteRepository: FavoritRepos = FavoritRepos(application)
    private val apiService = ApiConfig.getApiService()

    private val githubRepository: GithubRepos = GithubRepos(apiService)
    fun insert(favUser: FavoriteUsers) {
        mFavoriteRepository.insert(favUser)
    }

    fun delete(favUser: FavoriteUsers) {
        mFavoriteRepository.delete(favUser)
    }

    fun getFavoriteByUsername(username: String): LiveData<FavoriteUsers?> {
        return mFavoriteRepository.getUserFavoriteByUsername(username)
    }

    fun detailUser(search:String) = githubRepository.detailUser(search)

    fun loading(): LiveData<Boolean> = githubRepository.isLoading

    fun user():MutableLiveData<ResponseUser?> = githubRepository.detailUser

}