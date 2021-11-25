package com.shalan.searchgithub.base.network

import android.content.Context
import android.util.Log.VERBOSE
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.shalan.searchgithub.base.services.SessionService
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

abstract class RetrofitClientImpl(
    private val context: Context,
    private val adapterFactory: CallAdapter.Factory,
    private val converterFactory: Converter.Factory,
    private val sessionService: SessionService
) : IRestClient {

    private val REQUEST_DURATION = 1L

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var retrofitClient: Retrofit

    override fun getOkHttpClient(): OkHttpClient =
        if (this::okHttpClient.isInitialized) okHttpClient else buildOkHttpClient()

    private fun buildOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {

        //ADDING PROVIDED INTERCEPTORS
        getAdditionalInterceptors()?.forEach { interceptor ->
            this.addInterceptor(interceptor)
        }

        //ADDING HEADERS
        getAdditionalHeaders()?.let { headers ->
            this.addInterceptor { chain ->
                val newRequestBuilder = chain.request().newBuilder()
                if (sessionService.hasValidSession())
                    newRequestBuilder.addHeader("Authorization", "token ${sessionService.getSessionToken()}")
                for ((key, value) in headers) {
                    newRequestBuilder.addHeader(key, value)
                }
                chain.proceed(newRequestBuilder.build())
            }
        }


        //ADDING LOGGING INTERCEPTOR
        this.addInterceptor(
            LoggingInterceptor.Builder().setLevel(Level.BASIC).log(VERBOSE).build()
        )

        //ADDING CHUCKER FOR INTERCEPTING NETWORK REQUESTS
        this.addInterceptor(ChuckerInterceptor.Builder(context).build())

        this.writeTimeout(REQUEST_DURATION, TimeUnit.MINUTES)
        this.connectTimeout(REQUEST_DURATION, TimeUnit.MINUTES)
        this.readTimeout(REQUEST_DURATION, TimeUnit.MINUTES)
        this.callTimeout(REQUEST_DURATION, TimeUnit.MINUTES)
    }.build().also {
        okHttpClient = it
    }

    override fun getNetworkClient(): Retrofit =
        if (this::retrofitClient.isInitialized) retrofitClient else buildRetrofitClient()


    private fun buildRetrofitClient(): Retrofit = Retrofit.Builder().apply {
        this.baseUrl(baseUrl)
        this.client(getOkHttpClient())
        this.addCallAdapterFactory(adapterFactory)
        getStringConverterFactory()?.let { stringConverter ->
            this.addConverterFactory(stringConverter)
        }
        this.addConverterFactory(converterFactory)
    }.build().also {
        retrofitClient = it
    }

    abstract fun getStringConverterFactory(): Converter.Factory?
}