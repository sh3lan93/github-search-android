package com.shalan.searchgithub.base.states

import com.shalan.searchgithub.base.network.errorhandling.CommonErrors

data class Result<T>(val status: ICommonStatus, val data: T?, val error: CommonErrors?) :
    IResult<T> {

    companion object {
        fun <T> loading() = Result<T>(CommonStatusImp.LOADING, null, null)

        fun <T> success() = Result<T>(CommonStatusImp.SUCCESS, null, null)

        fun <T> success(data: T?) = Result(CommonStatusImp.SUCCESS, data, null)

        fun <T> error(error: CommonErrors?) = Result<T>(CommonStatusImp.ERROR, null, error)
    }

    override fun fetchData(): T? = data

    override fun fetchError(): CommonErrors? = error

    override fun whichStatus(): ICommonStatus = status
}
