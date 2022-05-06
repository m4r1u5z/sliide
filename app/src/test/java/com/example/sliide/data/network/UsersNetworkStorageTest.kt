package com.example.sliide.data.network

import com.example.sliide.data.network.dto.UserDto
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking

class UsersNetworkStorageTest : TestCase() {

    @MockK(relaxed = true)
    lateinit var usersService: UsersService

    @InjectMockKs
    lateinit var usersNetworkStorage: UsersNetworkStorage

    override fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    fun testGetUsers() = runBlocking {
        // when
        usersNetworkStorage.getUsers()

        // then
        coVerify { usersService.getUsers() }
    }

    fun testAddUser() = runBlocking {
        // given
        val userDto = mockk<UserDto>()

        // when
        usersNetworkStorage.addUser(userDto)

        // then
        coVerify { usersService.addUser(userDto) }
    }

    fun testRemoveUser() = runBlocking {
        // given
        val userId = 1

        // when
        usersNetworkStorage.removeUser(userId)

        // then
        coVerify { usersService.removeUser(userId) }
    }
}
