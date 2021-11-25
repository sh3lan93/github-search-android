package com.shalan.searchgithub.base.services

import io.reactivex.rxjava3.core.Scheduler


interface SchedulerService {

    val mainThreadScheduler: Scheduler

    val ioScheduler: Scheduler

    val computationalScheduler: Scheduler
}
