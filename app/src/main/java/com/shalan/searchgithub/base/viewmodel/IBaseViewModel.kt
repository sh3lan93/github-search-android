package com.shalan.searchgithub.base.viewmodel

import androidx.lifecycle.LifecycleObserver
import org.koin.core.component.KoinComponent

interface IBaseViewModel : LifecycleObserver, KoinComponent {

    fun startLogic()

}