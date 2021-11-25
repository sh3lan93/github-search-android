package com.shalan.searchgithub.feature.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shalan.searchgithub.base.livedata.SingleLiveEvent
import com.shalan.searchgithub.base.states.IResult
import com.shalan.searchgithub.base.usecase.IUseCase
import com.shalan.searchgithub.base.usecase.SimpleUseCase
import com.shalan.searchgithub.base.viewmodel.BaseViewModel
import com.shalan.searchgithub.feature.search.domain.entities.GithubRepo
import com.shalan.searchgithub.feature.search.domain.usecase.SearchQueryParams
import com.shalan.searchgithub.feature.search.presentation.MainRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class MainViewModel(
    private val mainRepo: MainRepository,
    private val authorizationUseCase: SimpleUseCase<String, Completable>,
    private val fetchSearchResultUseCase: IUseCase<SearchQueryParams, Single<List<GithubRepo>>>
) :
    BaseViewModel() {


    private val isUserAuthenticatedResult: SingleLiveEvent<Boolean> by lazy {
        SingleLiveEvent()
    }

    val _isUserAuthenticatedResult: LiveData<Boolean> = isUserAuthenticatedResult

    private val authorizeUserResult: MutableLiveData<IResult<Any>> by lazy {
        MutableLiveData()
    }

    val _authorizeUserResult: LiveData<IResult<Any>> = authorizeUserResult

    private val searchResult: MutableLiveData<IResult<List<GithubRepo>>> by lazy {
        MutableLiveData()
    }

    val _searchResult: LiveData<IResult<List<GithubRepo>>> = searchResult

    override fun startLogic() {
        isUserAuthenticatedResult.value = mainRepo.isUserAuthenticated()
    }

    fun authorizeUser(code: String) {
        authorizationUseCase.execute(code).execute(authorizeUserResult)
    }

    fun search(query: String) {
        fetchSearchResultUseCase.execute(SearchQueryParams(query = query)).execute(searchResult)
    }
}