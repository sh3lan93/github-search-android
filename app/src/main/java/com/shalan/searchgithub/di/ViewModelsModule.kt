package com.shalan.searchgithub.di

import com.shalan.searchgithub.feature.search.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModules = module {
    viewModel {
        MainViewModel(
            mainRepo = get(),
            authorizationUseCase = get(),
            fetchSearchResultUseCase = get()
        )
    }
}