package com.shalan.searchgithub.di

import com.shalan.searchgithub.feature.search.data.repository.QuerySearchRepository
import org.koin.dsl.module


val repositoriesModule = module {
    factory {
        QuerySearchRepository(searchCachedDataSource = get(), searchRemoteDataSource = get())
    }
}