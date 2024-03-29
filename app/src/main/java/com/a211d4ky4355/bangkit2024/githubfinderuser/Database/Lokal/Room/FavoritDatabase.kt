package com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Entity.FavoriteUsers

@Database(entities = [FavoriteUsers::class], version = 1)
abstract class FavoritDatabase : RoomDatabase() {
    abstract fun favDao(): FavoritDao

    companion object {
        @Volatile
        private var INSTANCE: FavoritDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoritDatabase {
            if (INSTANCE == null) {
                synchronized(FavoritDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoritDatabase::class.java, "fav_db")
                        .build()
                }
            }
            return INSTANCE as FavoritDatabase
        }
    }
}