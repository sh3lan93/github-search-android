package com.shalan.searchgithub.database

import androidx.room.TypeConverter
import com.shalan.searchgithub.feature.search.data.remotemodels.Owner


class GithubOwnerConverter {

    @TypeConverter
    fun toJson(owner: Owner): String = owner.avatar

    @TypeConverter
    fun fromJson(value: String): Owner = Owner(value)
}