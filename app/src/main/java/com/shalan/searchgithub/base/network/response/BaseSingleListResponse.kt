package com.shalan.searchgithub.base.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class BaseSingleListResponse<T>(@Json(name = "items") val data: List<T>)
