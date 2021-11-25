package com.shalan.searchgithub.feature.search.data.remotemodels

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GithubSearchResponse(val items: List<RemoteGithubRepo>)
