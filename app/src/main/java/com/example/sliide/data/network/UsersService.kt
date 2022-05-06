package com.example.sliide.data.network

import com.example.sliide.data.network.dto.UserDto
import retrofit2.Response
import retrofit2.http.*

interface UsersService {

    @GET("/public/v2/users?access-token=88d9b4707f83306e3fe3e2c4ccee2d9078931e700afd3011ef590465f2d87281")
    suspend fun getUsers(): List<UserDto>

    @POST("/public/v2/users?access-token=88d9b4707f83306e3fe3e2c4ccee2d9078931e700afd3011ef590465f2d87281")
    suspend fun addUser(@Body user: UserDto)

    @DELETE("/public/v2/users/{userId}?access-token=88d9b4707f83306e3fe3e2c4ccee2d9078931e700afd3011ef590465f2d87281")
    suspend fun removeUser(@Path("userId") userId: Int): Response<Unit>
}
