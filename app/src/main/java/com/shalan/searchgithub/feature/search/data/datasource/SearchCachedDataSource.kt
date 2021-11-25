package com.shalan.searchgithub.feature.search.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shalan.searchgithub.feature.search.data.remotemodels.RemoteGithubRepo
import io.reactivex.rxjava3.core.Single


@Dao
interface SearchCachedDataSource {

    @Query("select * from remotegithubrepo")
    fun fetchCachedSearchResponse(): Single<List<RemoteGithubRepo>>

    @Insert
    fun saveFetchedSearchResponse(items: List<RemoteGithubRepo>)
}