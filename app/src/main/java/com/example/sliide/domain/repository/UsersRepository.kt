package com.example.sliide.domain.repository

import com.example.sliide.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getUsers(): Flow<List<User>>

    suspend fun addUser(user: User)
    suspend fun removeUser(userId: Int)
}
