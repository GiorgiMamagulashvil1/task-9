package com.example.workerexample

import android.app.Application
import android.content.Context
import com.example.workerexample.data.local.UserDao
import com.example.workerexample.data.local.UserDataBase

class App :Application(){

    override fun onCreate() {
        super.onCreate()
        App.instance = this

    }
    companion object {
        lateinit var instance :App

        private val database: UserDataBase by lazy {
             UserDataBase.databaseBuilder(instance)
        }
        val dao: UserDao by lazy {
             database.getDao()
        }
    }
}