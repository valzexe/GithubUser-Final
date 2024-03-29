package com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Control.Response

import com.google.gson.annotations.SerializedName

data class ResponseGithub(

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @field:SerializedName("items")
    val items: List<Items>

)

data class Items(

    @field:SerializedName("gists_url")
    val gistsUrl: String,

    @field:SerializedName("repos_url")
    val reposUrl: String,

    @field:SerializedName("following_url")
    val followingUrl: String,

    @field:SerializedName("starred_url")
    val starredUrl: String,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("followers_url")
    val followersUrl: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("subscriptions_url")
    val subscriptionsUrl: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("html_url")
    val htmlUrl: String,

    @field:SerializedName("id")
    val id: Int,

    )
