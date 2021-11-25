package com.shalan.searchgithub.base.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit


interface IRestClient : INetwork<Retrofit> {

    fun getOkHttpClient(): OkHttpClient

    fun getAdditionalHeaders(): Map<String, String>? = null

    fun getAdditionalInterceptors(): List<Interceptor>? = null

}