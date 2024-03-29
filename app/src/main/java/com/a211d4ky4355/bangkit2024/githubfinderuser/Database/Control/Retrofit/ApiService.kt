package com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Retrofit

import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.Items
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.ResponseGithub
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getGithub(
        @Query("q") q: String
    ): Call<ResponseGithub>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<ResponseUser>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<Items>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<Items>>
}