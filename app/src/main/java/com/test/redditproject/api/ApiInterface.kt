package com.test.redditproject.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/top.json")
    suspend fun getNext(
        @Query("after") after: String,
        @Query("limit") limit: String
    ): Response<ParentRequestModel>

    @GET("/top/.json")
    suspend fun getTop(): Response<ParentRequestModel>

}
