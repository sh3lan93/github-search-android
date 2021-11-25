package com.shalan.searchgithub.di

import com.shalan.searchgithub.base.services.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.internal.schedulers.ComputationScheduler
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MAIN_THREAD_SCHEDULER = "main_thread"
const val IO_SCHEDULER = "io_scheduler"
const val COMPUTATION_SCHEDULER = "computation_scheduler"

val servicesModule = module {

    single<Scheduler>(named(MAIN_THREAD_SCHEDULER)) { AndroidSchedulers.mainThread() }

    single<Scheduler>(named(IO_SCHEDULER)) { IoScheduler() }

    single<Scheduler>(named(COMPUTATION_SCHEDULER)) { ComputationScheduler() }

    single<SchedulerService> {
        SchedulerServiceImp(
            mainThreadScheduler = get(named(MAIN_THREAD_SCHEDULER)),
            ioScheduler = get(named(IO_SCHEDULER)),
            computationalScheduler = get(named(COMPUTATION_SCHEDULER))
        )
    }

    single<SerializationService> {
        SerializationServiceImp(moshi = get())
    }

    single<SessionService> {
        SessionServiceImp(sharedService = get(), serializationService = get())
    }

    single<SharedService> {
        SharedServiceImp(context = androidContext())
    }
}