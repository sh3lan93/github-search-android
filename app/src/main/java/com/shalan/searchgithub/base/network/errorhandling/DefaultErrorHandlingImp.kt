package com.shalan.searchgithub.base.network.errorhandling

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shalan.searchgithub.base.network.NetworkConstants
import com.shalan.searchgithub.base.states.IResult
import com.shalan.searchgithub.base.states.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


class DefaultErrorHandlingImp<T>(private val result: MutableLiveData<IResult<T>>) :
    IErrorHandling {

    companion object {
        val TAG = DefaultErrorHandlingImp::class.java.simpleName
    }

    override fun accept(t: Throwable?) {
        Log.e(TAG, "accept: ${t?.localizedMessage}")
        t?.let {
            when (it) {
                is HttpException -> handleHttpException(it)
                is IOException -> result.postValue(Result.error(CommonErrors.IOError))
                is SocketTimeoutException -> result.postValue(
                    Result.error(CommonErrors.SocketTimeoutError)
                )
                else -> result.postValue(Result.error(CommonErrors.UnknownError))
            }
        }
    }

    private fun handleHttpException(exception: HttpException) {
        when (exception.code()) {
            NetworkConstants.UNAUTHORIZED_CODE -> result.postValue(Result.error(CommonErrors.UnauthorizedError))
            NetworkConstants.FORBIDDEN_CODE -> result.postValue(Result.error(CommonErrors.ForbiddenError))
            NetworkConstants.INTERNAL_SERVER_ERROR_CODE -> result.postValue(
                Result.error(
                    CommonErrors.InternalServerError
                )
            )
            else -> result.postValue(Result.error(CommonErrors.UnknownError))
        }
    }

}
