package com.shalan.searchgithub.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shalan.searchgithub.feature.search.data.datasource.SearchCachedDataSource
import com.shalan.searchgithub.feature.search.data.remotemodels.RemoteGithubRepo


@Database(entities = [RemoteGithubRepo::class], version = 1)
@TypeConverters(GithubOwnerConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cachedGithubReposDAO(): SearchCachedDataSource
}