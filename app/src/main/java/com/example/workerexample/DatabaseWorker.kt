package com.example.workerexample

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.lang.Exception

class DatabaseWorker(ctx:Context, params:WorkerParameters): CoroutineWorker(ctx,params) {

    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        return try {
            val dao = App.dao
            val data = dao.getAll().toMutableList()
            data.subList(10,data.size).clear()
            dao.deleteAll()
            dao.insertData(data)
            Result.Success()
        }catch (e:Exception){
            Result.failure()
        }
    }
}