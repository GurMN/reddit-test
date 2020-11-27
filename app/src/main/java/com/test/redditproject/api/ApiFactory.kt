package com.test.redditproject.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private val SERVER_ADDRESS = "https://www.reddit.com"


    fun retrofit(baseUrl: String = SERVER_ADDRESS): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().apply {
            readTimeout(40, TimeUnit.SECONDS)
            writeTimeout(40, TimeUnit.SECONDS)
            connectTimeout(40, TimeUnit.SECONDS)
            addInterceptor(interceptor)
            addInterceptor { chain ->
                var request = chain.request()
                request = request.newBuilder().build()
                val response = chain.proceed(request)
                response
            }
        }.build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val API_SERVICE: ApiInterface = retrofit().create(ApiInterface::class.java)

}