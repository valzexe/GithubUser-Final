package com.a211d4ky4355.bangkit2024.githubfinderuser.ReposGit

import android.app.Application
import androidx.lifecycle.LiveData
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Entity.FavoriteUsers
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Room.FavoritDao
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Room.FavoritDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoritRepos (application: Application) {
    private val mFavDao: FavoritDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoritDatabase.getDatabase(application)
        mFavDao = db.favDao()
    }

    fun getAllFavorite(): LiveData<List<FavoriteUsers>> = mFavDao.getAllFavorite()

    fun getUserFavoriteByUsername(username:String): LiveData<FavoriteUsers?> =
        mFavDao.getFavoriteUserByUsername(username)


    fun insert(favUser: FavoriteUsers) {
        executorService.execute { mFavDao.insert(favUser) }
    }

    fun delete(favUser: FavoriteUsers) {
        executorService.execute { mFavDao.delete(favUser) }
    }
}