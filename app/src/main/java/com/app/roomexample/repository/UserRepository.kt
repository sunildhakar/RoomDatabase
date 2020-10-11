package com.app.roomexample.repository

import androidx.lifecycle.LiveData
import com.app.roomexample.data.UserDao
import com.app.roomexample.models.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }


    suspend fun updateUser(user: User) {
        userDao.updateUserdata(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }


    suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }
}