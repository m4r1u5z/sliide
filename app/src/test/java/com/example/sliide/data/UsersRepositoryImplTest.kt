package com.example.sliide.data

import com.example.sliide.data.network.UsersMapper
import com.example.sliide.data.network.UsersNetworkStorage
import com.example.sliide.data.network.dto.UserDto
import com.example.sliide.domain.entity.User
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class UsersRepositoryImplTest : TestCase() {

    @MockK(relaxed = true)
    lateinit var usersNetworkStorage: UsersNetworkStorage

    @MockK(relaxed = true)
    lateinit var mapper: UsersMapper

    @InjectMockKs
    lateinit var usersRepositoryImpl: UsersRepositoryImpl

    override fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    fun testGetUsers() = runBlocking {
        // given
        val userDto1 = mockk<UserDto>()
        val userDto2 = mockk<UserDto>()
        val user1 = mockk<User>()
        val user2 = mockk<User>()
        every { mapper.map(userDto1) } returns user1
        every { mapper.map(userDto2) } returns user2
        coEvery { usersNetworkStorage.getUsers() } returns listOf(userDto1, userDto2)

        // when
        val users = usersRepositoryImpl.getUsers().first()

        // then
        assertEquals(user1, users[0])
        assertEquals(user2, users[1])
    }

    fun testAddUser() = runBlocking {
        // given
        val user = mockk<User>()
        val userDto = mockk<UserDto>()
        every { mapper.map(user) } returns userDto

        // when
        usersRepositoryImpl.addUser(user)

        // then
        coVerify { usersNetworkStorage.addUser(userDto) }
    }

    fun testRemoveUser() = runBlocking {
        // given
        val userId = 1

        // when
        usersRepositoryImpl.removeUser(userId)

        // then
        coVerify { usersNetworkStorage.removeUser(userId) }
    }
}
