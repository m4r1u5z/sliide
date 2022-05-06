package com.example.sliide.data.network

import com.example.sliide.data.network.dto.UserDto
import com.example.sliide.domain.entity.User
import junit.framework.TestCase

class UsersMapperTest : TestCase() {

    private val usersMapper = UsersMapper()

    fun `test map UserDto to User`() {
        // given
        val dto = UserDto(
            id = 1,
            name = "Name",
            email = "Email",
        )

        // when
        val user = usersMapper.map(dto)

        // then
        assertEquals(1, user.id)
        assertEquals("Name", user.name)
        assertEquals("Email", user.email)
    }

    fun `test map User to UserDto`() {
        // given
        val user = User(
            id = 1,
            name = "Name",
            email = "Email",
        )

        // when
        val dto = usersMapper.map(user)

        // then
        assertEquals(1, dto.id)
        assertEquals("Name", dto.name)
        assertEquals("Email", dto.email)
    }
}
