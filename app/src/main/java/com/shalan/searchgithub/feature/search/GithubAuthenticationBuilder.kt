package com.shalan.searchgithub.feature.search

import android.net.Uri
import com.shalan.searchgithub.BuildConfig

object GithubAuthenticationBuilder {

    const val CODE_PARAM_KEY = "code"

    private const val GITHUB_AUTHORITY = "github.com"
    private const val GITHUB_AUTH_PATH = "login/oauth/authorize"
    private const val CLIENT_ID_PARAM_KEY = "client_id"
    private const val ALLOW_SIGNUP_PARAM_KEY = "allow_signup"
    private const val REDIRECTION_URI_PARAM_KEY = "redirect_uri"
    private const val ALLOW_SIGNUP_VALUE = "false"
    private const val REDIRECTION_URI = "https://searchgithub.shalan.com/callback"

    fun buildAuthUri(): Uri = Uri.Builder().scheme("https")
        .authority(GITHUB_AUTHORITY)
        .appendEncodedPath(GITHUB_AUTH_PATH)
        .appendQueryParameter(CLIENT_ID_PARAM_KEY, BuildConfig.CLIENT_ID)
        .appendQueryParameter(ALLOW_SIGNUP_PARAM_KEY, ALLOW_SIGNUP_VALUE)
        .appendQueryParameter(REDIRECTION_URI_PARAM_KEY, REDIRECTION_URI)
        .build()
}