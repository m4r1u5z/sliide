package com.example.sliide.data.network

import com.example.sliide.data.network.dto.UserDto
import javax.inject.Inject

class UsersNetworkStorage @Inject constructor(
    private val usersService: UsersService,
) {

    suspend fun getUsers(): List<UserDto> = usersService.getUsers()

    suspend fun addUser(user: UserDto) {
        usersService.addUser(user)
    }

    suspend fun removeUser(userId: Int) {
        usersService.removeUser(userId)
    }
}
