package com.shalan.searchgithub.feature.search.data.repository

import com.shalan.searchgithub.base.mapper.Mapper
import com.shalan.searchgithub.feature.search.data.datasource.SearchCachedDataSource
import com.shalan.searchgithub.feature.search.data.datasource.SearchRemoteDataSource
import com.shalan.searchgithub.feature.search.data.remotemodels.Owner
import com.shalan.searchgithub.feature.search.data.remotemodels.RemoteGithubRepo
import com.shalan.searchgithub.feature.search.domain.entities.GithubRepo
import io.reactivex.rxjava3.core.Single

class QuerySearchRepository(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val searchCachedDataSource: SearchCachedDataSource
) : Mapper<RemoteGithubRepo, GithubRepo> {

    fun fetchSearchResult(
        query: String,
        perPage: Int,
        page: Int,
        sort: String,
        order: String
    ): Single<List<RemoteGithubRepo>> =
        searchRemoteDataSource.fetchSearch(query, perPage, page, sort, order)
            .map { it.items }
            .doOnSuccess {
                searchCachedDataSource.saveFetchedSearchResponse(it)
            }
            .onErrorResumeNext { searchCachedDataSource.fetchCachedSearchResponse() }


    override fun mapFromEntity(entity: RemoteGithubRepo): GithubRepo = GithubRepo(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        avatar = entity.owner.avatar
    )

    override fun mapFromModel(model: GithubRepo): RemoteGithubRepo = RemoteGithubRepo(
        id = model.id,
        name = model.name,
        description = model.description,
        owner = Owner(avatar = model.avatar)
    )
}