package com.example.sliide.domain.usecase

import com.example.sliide.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UsersUseCase {

    fun getUsers(): Flow<List<User>>

    suspend fun addUser(user: User)
    suspend fun removeUser(user: User)
}
