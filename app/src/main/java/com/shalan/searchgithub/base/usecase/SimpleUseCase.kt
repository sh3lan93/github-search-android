package com.shalan.searchgithub.base.usecase

interface SimpleUseCase<Params, Result> {

    fun execute(input: Params): Result

}