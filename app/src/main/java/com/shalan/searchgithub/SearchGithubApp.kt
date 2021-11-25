package com.shalan.searchgithub

import android.app.Application
import com.shalan.searchgithub.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SearchGithubApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SearchGithubApp)
            modules(
                networkModule,
                presentationReposModule,
                viewModelModules,
                servicesModule,
                useCasesModule,
                networkServiceModule,
                databaseModule,
                repositoriesModule
            )
        }
    }
}