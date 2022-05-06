package com.example.sliide.data.network

import com.example.sliide.data.network.dto.UserDto
import com.example.sliide.domain.entity.User

class UsersMapper {

    fun map(dto: UserDto) = User(
        id = dto.id,
        name = dto.name,
        email = dto.email,
        createdAt = System.currentTimeMillis(),
    )

    fun map(user: User) = UserDto(
        id = user.id,
        name = user.name,
        email = user.email,
    )
}
