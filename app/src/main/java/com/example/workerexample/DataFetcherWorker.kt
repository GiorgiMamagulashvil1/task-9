package com.example.workerexample

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workerexample.data.model.User
import com.example.workerexample.data.remote.RetrofitInstance

class DataFetcherWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        return try {
            val response = RetrofitInstance.api.getUsers()
            if (response.isSuccessful) {
                val data = response.body()?.data
                data?.let {
                    App.dao.insertData(data)
                }
                Result.Success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }
}