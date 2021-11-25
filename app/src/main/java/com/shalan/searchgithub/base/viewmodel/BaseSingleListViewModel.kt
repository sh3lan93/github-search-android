package com.shalan.searchgithub.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shalan.searchgithub.base.states.IResult
import io.reactivex.rxjava3.core.Single


abstract class BaseSingleListViewModel<T>() : BaseViewModel() {

    var refreshData: Boolean = false

    protected val data: MutableLiveData<IResult<T>> by lazy {
        MutableLiveData()
    }

    val data_: LiveData<IResult<T>>
        get() = data

    override fun startLogic() {
        super.startLogic()
        if (refreshData || data.value?.fetchData() == null) {
            loadData().execute(data)
            refreshData = false
        }
    }

    abstract fun loadData(): Single<T>

}
