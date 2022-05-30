package com.example.workerexample.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {
        return@lazy Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/").build()
    }
    val api: ServiceApi by lazy { return@lazy retrofit.create(ServiceApi::class.java) }
}