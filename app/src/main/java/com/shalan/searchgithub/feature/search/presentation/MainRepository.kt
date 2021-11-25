package com.shalan.searchgithub.feature.search.presentation

import com.shalan.searchgithub.base.services.SessionService

class MainRepository(private val sessionService: SessionService) {

    fun isUserAuthenticated() = sessionService.hasValidSession()
}