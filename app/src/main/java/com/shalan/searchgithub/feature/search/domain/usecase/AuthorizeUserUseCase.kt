package com.shalan.searchgithub.feature.search.domain.usecase

import com.shalan.searchgithub.base.services.SessionService
import com.shalan.searchgithub.base.usecase.SimpleUseCase
import com.shalan.searchgithub.network.services.AuthorizationService
import io.reactivex.rxjava3.core.Completable

class AuthorizeUserUseCase(
    private val authorizeService: AuthorizationService,
    private val sessionService: SessionService
) :
    SimpleUseCase<String, Completable> {

    override fun execute(input: String): Completable = Completable.fromSingle(
        authorizeService.authorizeUser(code = input).doOnSuccess { response ->
            sessionService.saveSessionToken(response.token)
        })
}