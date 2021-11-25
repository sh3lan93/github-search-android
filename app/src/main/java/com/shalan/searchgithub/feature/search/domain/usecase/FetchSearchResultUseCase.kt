package com.shalan.searchgithub.feature.search.domain.usecase

import com.shalan.searchgithub.base.mapper.Mapper
import com.shalan.searchgithub.base.usecase.IUseCase
import com.shalan.searchgithub.feature.search.data.remotemodels.Owner
import com.shalan.searchgithub.feature.search.data.remotemodels.RemoteGithubRepo
import com.shalan.searchgithub.feature.search.data.repository.QuerySearchRepository
import com.shalan.searchgithub.feature.search.domain.entities.GithubRepo
import io.reactivex.rxjava3.core.Single

class FetchSearchResultUseCase(private val repository: QuerySearchRepository) :
    IUseCase<SearchQueryParams, Single<List<GithubRepo>>> {


    override fun execute(input: SearchQueryParams): Single<List<GithubRepo>> =
        repository.fetchSearchResult(
            query = input.query,
            perPage = input.perPage,
            page = input.page,
            order = input.order,
            sort = input.sort
        ).map {
            it.fold(mutableListOf()) { acc: MutableList<GithubRepo>, remoteGithubRepo: RemoteGithubRepo ->
                acc.add(repository.mapFromEntity(remoteGithubRepo))
                acc
            }
        }




}