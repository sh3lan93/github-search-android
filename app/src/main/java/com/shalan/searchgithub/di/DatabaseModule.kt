package com.shalan.searchgithub.di

import androidx.room.Room
import com.shalan.searchgithub.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_database").build()
    }

    factory {
        get<AppDatabase>().cachedGithubReposDAO()
    }
}