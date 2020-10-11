package com.app.roomexample.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.roomexample.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUserdata(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Delete FROM user_table")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>


}