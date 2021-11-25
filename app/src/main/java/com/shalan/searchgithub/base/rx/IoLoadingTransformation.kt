package com.shalan.searchgithub.base.rx

import androidx.lifecycle.MutableLiveData
import com.shalan.searchgithub.base.services.SchedulerService
import com.shalan.searchgithub.base.states.IResult
import com.shalan.searchgithub.base.states.Result
import io.reactivex.rxjava3.core.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IoLoadingTransformation<T>(private val result: MutableLiveData<IResult<T>>) :
    ObservableTransformer<T, T>,
    SingleTransformer<T, T>,
    CompletableTransformer, MaybeTransformer<T, T>, KoinComponent {

    private val schedulerService: SchedulerService by inject()

    override fun apply(upstream: Observable<T>): ObservableSource<T> = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.ioScheduler)
        .observeOn(schedulerService.mainThreadScheduler)

    override fun apply(upstream: Single<T>): SingleSource<T> = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.ioScheduler)
        .observeOn(schedulerService.ioScheduler)

    override fun apply(upstream: Completable): CompletableSource = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.ioScheduler)
        .observeOn(schedulerService.ioScheduler)

    override fun apply(upstream: Maybe<T>): MaybeSource<T> = upstream
        .doOnSubscribe {
            result.postValue(Result.loading())
        }.subscribeOn(schedulerService.ioScheduler)
        .observeOn(schedulerService.ioScheduler)
}
