package com.test.redditproject.api

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val errorCode: Int = -1, var errMsg: String = "") : Result<Nothing>()
}