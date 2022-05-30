package com.example.workerexample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workerexample.data.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun getDao(): UserDao

    companion object {
        fun databaseBuilder(context: Context): UserDataBase {
            return Room.databaseBuilder(context, UserDataBase::class.java, "my_db").build()
        }
    }
}