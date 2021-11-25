package com.shalan.searchgithub.feature.search.data.datasource

import com.shalan.searchgithub.feature.search.data.remotemodels.GithubSearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteDataSource {

    @GET("search/repositories")
    fun fetchSearch(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): Single<GithubSearchResponse>
}