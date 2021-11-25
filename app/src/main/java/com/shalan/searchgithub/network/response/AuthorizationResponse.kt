package com.shalan.searchgithub.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorizationResponse(
    @Json(name = "access_token") val token: String,
    @Json(name = "token_type") val tokenType: String
)
