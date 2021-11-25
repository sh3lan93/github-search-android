package com.shalan.searchgithub.feature.search.domain.usecase

import com.shalan.searchgithub.base.usecase.IParams

data class SearchQueryParams(
    val query: String,
    val perPage: Int = 10,
    val page: Int = 1,
    val sort: String = "stars",
    val order: String = "desc"
) : IParams
