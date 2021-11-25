package com.shalan.searchgithub.network.services

import com.shalan.searchgithub.BuildConfig
import com.shalan.searchgithub.network.response.AuthorizationResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST
import retrofit2.http.Query


interface AuthorizationService {

    @POST("https://github.com/login/oauth/access_token")
    fun authorizeUser(
        @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Query("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Query("code") code: String,
        @Query("redirect_uri") redirectionUrl: String = BuildConfig.GITHUB_REDIRECTION_URL
    ): Single<AuthorizationResponse>
}