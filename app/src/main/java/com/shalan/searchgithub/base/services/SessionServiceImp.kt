package com.shalan.searchgithub.base.services


class SessionServiceImp(
    private val sharedService: SharedService,
    private val serializationService: SerializationService
) : SessionService {

    companion object {
        val TOKEN_KEY = "user-token"
        val REFRESH_TOKEN_KEY = "user-refresh-token"
    }

    override fun saveSessionToken(token: String) {
        sharedService.save(TOKEN_KEY, token)
    }

    override fun saveSession(token: String, refreshToken: String) {
        sharedService.save(TOKEN_KEY, token)
        sharedService.save(REFRESH_TOKEN_KEY, refreshToken)
    }

    override fun getSessionToken(): String? = sharedService.get(TOKEN_KEY, "").let {
        if (it.isNotEmpty())
            it
        else
            null
    }

    override fun clearSession() {
        sharedService.remove(TOKEN_KEY)
    }
}
