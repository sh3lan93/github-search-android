package com.shalan.searchgithub.base.states

import com.shalan.searchgithub.base.network.errorhandling.CommonErrors


interface IResult<T> {

	fun fetchData(): T?

	fun fetchError(): CommonErrors?

	fun whichStatus(): ICommonStatus
}
