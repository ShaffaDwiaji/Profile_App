package com.shaffadwiaji.profileapp.repository

import com.shaffadwiaji.profileapp.data.User
import com.shaffadwiaji.profileapp.data.UserDao

class UserRepository(private val userDao: UserDao) {

    // Fungsi untuk register user baru
    suspend fun register(user: User) {
        userDao.insert(user)
    }

    // Fungsi untuk login user berdasarkan email dan password
    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }

    // Fungsi untuk ambil user berdasarkan email
    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    // Fungsi untuk update profil user
    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}