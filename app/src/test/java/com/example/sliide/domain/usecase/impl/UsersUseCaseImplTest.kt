package com.example.sliide.domain.usecase.impl

import com.example.sliide.domain.entity.User
import com.example.sliide.domain.repository.UsersRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class UsersUseCaseImplTest : TestCase() {

    @MockK(relaxed = true)
    lateinit var usersRepository: UsersRepository

    @InjectMockKs
    lateinit var usersUseCaseImpl: UsersUseCaseImpl

    override fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    fun testGetUsers() = runBlocking {
        // given
        val user1 = mockk<User>()
        val user2 = mockk<User>()
        every { usersRepository.getUsers() } returns flow { emit(listOf(user1, user2)) }

        // when
        val users = usersUseCaseImpl.getUsers().first()

        // then
        assertEquals(user1, users[0])
        assertEquals(user2, users[1])
    }

    fun testAddUser() = runBlocking {
        // given
        val user = mockk<User>()

        // when
        usersUseCaseImpl.addUser(user)

        // then
        coVerify { usersRepository.addUser(user) }
    }

    fun testRemoveUser() = runBlocking {
        // given
        val user = mockk<User>()
        val userId = 1
        every { user.id } returns userId

        // when
        usersUseCaseImpl.removeUser(user)

        // then
        coVerify { usersRepository.removeUser(userId) }
    }
}
