package com.shalan.searchgithub.base.services

import io.reactivex.rxjava3.core.Scheduler

class SchedulerServiceImp(
    override val mainThreadScheduler: Scheduler,
    override val ioScheduler: Scheduler,
    override val computationalScheduler: Scheduler
) : SchedulerService
