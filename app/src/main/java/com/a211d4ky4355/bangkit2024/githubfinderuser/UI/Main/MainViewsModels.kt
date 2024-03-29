package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.Items
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Retrofit.ApiConfig
import com.a211d4ky4355.bangkit2024.githubfinderuser.ReposGit.GithubRepos
import com.a211d4ky4355.bangkit2024.githubfinderuser.Perference.SettingsPerference

class MainViewsModels(private val pref: SettingsPerference) :ViewModel() {

    private val apiService = ApiConfig.getApiService()

    private val githubRepository: GithubRepos = GithubRepos(apiService)
    fun getItems(search:String) = githubRepository.findUsers(search)

    fun user():LiveData<List<Items>> = githubRepository.listUser

    fun loading():LiveData<Boolean> = githubRepository.isLoading

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }
}