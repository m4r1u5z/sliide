package com.example.sliide.domain.usecase.impl

import com.example.sliide.domain.entity.User
import com.example.sliide.domain.repository.UsersRepository
import com.example.sliide.domain.usecase.UsersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : UsersUseCase {

    override fun getUsers(): Flow<List<User>> = usersRepository.getUsers()

    override suspend fun addUser(user: User) {
        usersRepository.addUser(user)
    }

    override suspend fun removeUser(user: User) {
        usersRepository.removeUser(user.id)
    }
}
