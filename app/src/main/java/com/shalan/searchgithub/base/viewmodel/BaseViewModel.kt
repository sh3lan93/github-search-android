package com.shalan.searchgithub.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalan.searchgithub.base.network.errorhandling.DefaultErrorHandlingImp
import com.shalan.searchgithub.base.network.errorhandling.IErrorHandling
import com.shalan.searchgithub.base.rx.IoLoadingTransformation
import com.shalan.searchgithub.base.states.IResult
import com.shalan.searchgithub.base.states.Result
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

open class BaseViewModel(
) : ViewModel(), IBaseViewModel {

    protected val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun startLogic() {

    }

    private fun <T> getIOLoadingTransformers(result: MutableLiveData<IResult<T>>) =
        IoLoadingTransformation(result)

    fun <T> Observable<T>.execute(result: MutableLiveData<IResult<T>>) {
        this.compose(getIOLoadingTransformers(result))
            .subscribe(
                {
                    result.value = Result.success(it)
                },
                DefaultErrorHandlingImp(result = result)
            ).addTo(compositeDisposable)
    }

    fun <T> Single<T>.execute(result: MutableLiveData<IResult<T>>) {
        this.compose(getIOLoadingTransformers(result))
            .subscribe(
                {
                    result.postValue(Result.success(it))
                }, DefaultErrorHandlingImp(result = result)
            ).addTo(compositeDisposable)
    }

    fun <T> Maybe<T>.execute(
        result: MutableLiveData<IResult<T>>
    ) {
        this.compose(getIOLoadingTransformers(result))
            .subscribe(
                {
                    result.postValue(Result.success(it))
                }, DefaultErrorHandlingImp(result = result)
            ).addTo(compositeDisposable)
    }

    fun Completable.execute(
        result: MutableLiveData<IResult<Any>>
    ) {
        this.compose(getIOLoadingTransformers(result))
            .subscribe(
                {
                    result.postValue(Result.success())
                }, DefaultErrorHandlingImp(result = result)
            ).addTo(compositeDisposable)
    }

    fun <T, Error : IErrorHandling> Single<T>.execute(
        result: MutableLiveData<IResult<T>>,
        error: Error
    ) {
        this.compose(getIOLoadingTransformers(result))
            .subscribe({
                result.value = Result.success(it)
            }, error)
    }
}