package com.shalan.searchgithub.network

import android.content.Context
import com.shalan.searchgithub.BuildConfig
import com.shalan.searchgithub.base.network.RetrofitClientImpl
import com.shalan.searchgithub.base.services.SessionService
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.scalars.ScalarsConverterFactory

class AppNetworkImpl(
    context: Context,
    adapterFactory: CallAdapter.Factory,
    converterFactory: Converter.Factory,
    private val stringConverterFactory: ScalarsConverterFactory,
    sessionService: SessionService
) : RetrofitClientImpl(context, adapterFactory, converterFactory, sessionService) {


    override fun getStringConverterFactory(): Converter.Factory = stringConverterFactory

    override val baseUrl: String
        get() = BuildConfig.BASE_URL

    override fun getAdditionalHeaders(): Map<String, String> =
        mapOf(Pair("Accept", "application/json"))
}