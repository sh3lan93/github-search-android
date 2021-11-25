package com.shalan.searchgithub.feature.search.data.remotemodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Owner(@Json(name = "avatar_url") val avatar: String)
