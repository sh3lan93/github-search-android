package com.shalan.searchgithub.base.network


interface INetwork<T> {

    fun getNetworkClient(): T

    val baseUrl: String
}