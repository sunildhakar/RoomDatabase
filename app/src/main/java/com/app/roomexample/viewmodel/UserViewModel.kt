package com.app.roomexample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app.roomexample.data.UserDataBase
import com.app.roomexample.models.User
import com.app.roomexample.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>

    private val repository: UserRepository

    init {
        val userDao = UserDataBase.getUserDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }


    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUserData(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }


    fun deleteAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUser()
        }
    }


}