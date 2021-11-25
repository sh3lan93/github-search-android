package com.shalan.searchgithub.base.network.errorhandling

sealed class CommonErrors {

    object UnauthorizedError: CommonErrors()

    object ForbiddenError: CommonErrors()

    object InternalServerError: CommonErrors()

    object NotFoundError: CommonErrors()

    object UnknownError: CommonErrors()

    object IOError: CommonErrors()

    object SocketTimeoutError: CommonErrors()

}
