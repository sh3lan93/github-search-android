package com.shalan.searchgithub.di

import com.shalan.searchgithub.base.usecase.IUseCase
import com.shalan.searchgithub.base.usecase.SimpleUseCase
import com.shalan.searchgithub.feature.search.domain.entities.GithubRepo
import com.shalan.searchgithub.feature.search.domain.usecase.AuthorizeUserUseCase
import com.shalan.searchgithub.feature.search.domain.usecase.FetchSearchResultUseCase
import com.shalan.searchgithub.feature.search.domain.usecase.SearchQueryParams
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.koin.dsl.module


val useCasesModule = module {
    factory<SimpleUseCase<String, Completable>> {
        AuthorizeUserUseCase(authorizeService = get(), sessionService = get())
    }

    factory<IUseCase<SearchQueryParams, Single<List<GithubRepo>>>> {
        FetchSearchResultUseCase(repository = get())
    }
}