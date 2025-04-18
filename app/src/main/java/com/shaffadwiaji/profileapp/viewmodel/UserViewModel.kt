package com.shaffadwiaji.profileapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shaffadwiaji.profileapp.data.AppDatabase
import com.shaffadwiaji.profileapp.data.User
import com.shaffadwiaji.profileapp.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    // Fungsi register user
    fun register(user: User) = viewModelScope.launch {
        repository.register(user)
    }

    // Fungsi login user
    suspend fun login(email: String, password: String): User? {
        return repository.login(email, password)
    }

    // Ambil data user berdasarkan email
    suspend fun getUserByEmail(email: String): User? {
        return repository.getUserByEmail(email)
    }

    // Update data user
    fun updateUser(user: User) = viewModelScope.launch {
        repository.updateUser(user)
    }
}