package com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Entity.FavoriteUsers

@Dao
interface FavoritDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insert(favUser: FavoriteUsers)

        @Update
        fun update(favUser: FavoriteUsers)

        @Delete
        fun delete(favUser: FavoriteUsers)

        @Query("SELECT  * from FavoriteUsers")
        fun getAllFavorite(): LiveData<List<FavoriteUsers>>

        @Query("SELECT * FROM FavoriteUsers WHERE username = :username")
        fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUsers?>
}