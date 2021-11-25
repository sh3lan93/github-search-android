package com.shalan.searchgithub.base.services


interface SessionService {

    fun saveSessionToken(token: String)

    fun saveSession(token: String, refreshToken: String)

    fun getSessionToken(): String?

    fun hasValidSession(): Boolean = getSessionToken() != null

    fun clearSession()
}
