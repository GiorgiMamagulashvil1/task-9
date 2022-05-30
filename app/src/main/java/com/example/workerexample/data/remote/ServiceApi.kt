package com.example.workerexample.data.remote

import com.example.workerexample.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("/api/users")
    suspend fun getUsers(
        @Query("page") page:Int = 1,
        @Query("per_page") perPage :Int = 12
    ):Response<UserResponse>

}