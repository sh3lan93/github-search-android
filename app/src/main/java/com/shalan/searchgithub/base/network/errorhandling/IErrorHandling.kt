package com.shalan.searchgithub.base.network.errorhandling

import io.reactivex.rxjava3.functions.Consumer

interface IErrorHandling : Consumer<Throwable>
