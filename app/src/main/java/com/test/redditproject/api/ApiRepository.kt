package com.test.redditproject.api

import android.util.Log
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ApiRepository {

    val apiInterface = ApiFactory.API_SERVICE
    val ERROR_TIMEOUT = 408
    val ERROR_NO_INTERNET = 499

    companion object {

        private var repo: ApiRepository? = null
        fun getInstance(): ApiRepository {
            if (repo == null) {
                repo = ApiRepository()
            }
            return repo as ApiRepository
        }
    }

    suspend fun getTop(): Result<Any>? {
        val call: suspend () -> Response<ParentRequestModel> = { apiInterface.getTop() }
        return safetyCall(call)
    }

    suspend fun getNext(after: String, limit: String): Result<Any>? {
        val call: suspend () -> Response<ParentRequestModel> =
            { apiInterface.getNext(after, limit) }
        return safetyCall(call)
    }

    suspend fun <T : Any> safetyCall(call: suspend () -> Response<T>): Result<Any>? {
        var response: Result<Any>?
        try {
            val result: Response<T> = call.invoke()
            response = Result.Success<T>(result.body()!!)
        } catch (e: Exception) {
            response = Result.Error(errMsg = e.message ?: "")
            Log.e("http ", e.message)
            when (e) {
                is SocketTimeoutException -> response = Result.Error(ERROR_TIMEOUT)
                is UnknownHostException -> response = Result.Error(ERROR_NO_INTERNET)
            }
        }
        return response
    }

}