package com.example.workerexample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.workerexample.data.model.User
import retrofit2.http.DELETE

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: List<User>)

    @Query("select * from user_table")
    suspend fun getAll():List<User>

    @Query("DELETE from user_table")
    suspend fun deleteAll()

}