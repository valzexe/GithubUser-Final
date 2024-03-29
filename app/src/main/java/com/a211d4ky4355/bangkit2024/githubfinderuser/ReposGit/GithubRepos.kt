package com.a211d4ky4355.bangkit2024.githubfinderuser.ReposGit

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.Items
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.ResponseGithub
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.ResponseUser
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepos (private val apiService: ApiService) {
    fun findUsers(search:String) {
        _isLoading.value = true
        val client = apiService.getGithub(search)
        client.enqueue(object : Callback<ResponseGithub> {
            override fun onResponse(
                call: Call<ResponseGithub>,
                response: Response<ResponseGithub>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listUser.value = responseBody.items
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseGithub>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun detailUser(receivedData:String) {
        _isLoading.value = true
        val client = apiService.getDetailUser(receivedData)
        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(
                call: Call<ResponseUser>,
                response: Response<ResponseUser>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detailUser.value = responseBody
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    private val _listUser = MutableLiveData<List<Items>>()
    val listUser: LiveData<List<Items>> = _listUser

    private var _detailUser = MutableLiveData<ResponseUser?>()
    val detailUser: MutableLiveData<ResponseUser?> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

}