package com.example.sliide.data.network.dto

import com.example.sliide.data.*
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName(COLUMN_ID)
    val id: Int,
    @SerializedName(COLUMN_NAME)
    val name: String,
    @SerializedName(COLUMN_EMAIL)
    val email: String,
    @SerializedName(COLUMN_GENDER)
    val gender: String = "male",
    @SerializedName(COLUMN_STATUS)
    val status: String = "active",
)
