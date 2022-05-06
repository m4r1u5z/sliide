package com.example.sliide.data

import com.example.sliide.data.network.UsersMapper
import com.example.sliide.data.network.UsersNetworkStorage
import com.example.sliide.domain.entity.User
import com.example.sliide.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersNetworkStorage: UsersNetworkStorage,
    private val mapper: UsersMapper,
) : UsersRepository {

    override fun getUsers(): Flow<List<User>> =
        flow {
            emit(usersNetworkStorage.getUsers().map(mapper::map))
        }

    override suspend fun addUser(user: User) {
        usersNetworkStorage.addUser(mapper.map(user))
    }

    override suspend fun removeUser(userId: Int) {
        usersNetworkStorage.removeUser(userId)
    }
}
