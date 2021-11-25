package com.shalan.searchgithub.di

import com.shalan.searchgithub.feature.search.presentation.MainRepository
import org.koin.dsl.module


val presentationReposModule = module {

    factory {
        MainRepository(sessionService = get())
    }
}