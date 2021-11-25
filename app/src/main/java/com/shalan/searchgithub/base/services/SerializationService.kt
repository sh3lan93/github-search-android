package com.shalan.searchgithub.base.services

interface SerializationService {

	fun <T> serialize(value: T, clazz: Class<T>): String

	fun <T> deserialize(value: String, clazz: Class<T>): T?
}
