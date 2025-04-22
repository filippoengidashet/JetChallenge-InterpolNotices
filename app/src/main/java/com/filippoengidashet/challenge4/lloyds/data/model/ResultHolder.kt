package com.filippoengidashet.challenge4.lloyds.data.model

sealed class ResultHolder<out T> {
    data class Success<out T>(val value: T) : ResultHolder<T>()
    data class Failure(val error: Throwable) : ResultHolder<Nothing>()
}
