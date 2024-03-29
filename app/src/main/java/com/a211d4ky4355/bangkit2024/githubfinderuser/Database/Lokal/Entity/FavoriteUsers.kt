package com.a211d4ky4355.bangkit2024.githubfinderuser.Database.Lokal.Entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteUsers(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "username")
    var login: String? = null,

    @ColumnInfo(name = "avatar")
    var avatarUrl: String? = null,

    ): Parcelable
