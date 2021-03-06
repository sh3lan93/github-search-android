package com.shalan.searchgithub.base.services


interface SharedService {

    fun <T> save(key: String, value: T)

    fun <T> get(key: String, defaultValue: T): T

    fun remove(key: String)
}
