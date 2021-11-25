package com.shalan.searchgithub.di

import com.shalan.searchgithub.base.network.IRestClient
import com.shalan.searchgithub.feature.search.data.datasource.SearchRemoteDataSource
import com.shalan.searchgithub.network.AppNetworkImpl
import com.shalan.searchgithub.network.services.AuthorizationService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val networkModule = module {

    single<CallAdapter.Factory> {
        RxJava3CallAdapterFactory.create()
    }

    single<Converter.Factory> {
        MoshiConverterFactory.create()
    }

    single<ScalarsConverterFactory> {
        ScalarsConverterFactory.create()
    }

    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single<IRestClient> {
        AppNetworkImpl(
            context = androidContext(),
            adapterFactory = get(),
            converterFactory = get(),
            stringConverterFactory = get(),
            sessionService = get()
        )
    }
}

val networkServiceModule = module {
    factory {
        get<IRestClient>().getNetworkClient().create(AuthorizationService::class.java)
    }

    factory {
        get<IRestClient>().getNetworkClient().create(SearchRemoteDataSource::class.java)
    }
}