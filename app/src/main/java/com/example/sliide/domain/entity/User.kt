package com.example.sliide.domain.entity

import android.text.TextUtils
import android.util.Patterns
import kotlin.random.Random

data class User(
    val id: Int = Random.nextInt(),
    val name: String,
    val email: String,
    val createdAt: Long = System.currentTimeMillis(),
)

fun User.isValid() = this.name.isValidName() && this.email.isValidEmail()

private fun String.isValidName(): Boolean = this.length > NAME_MIN_LENGTH

private fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

private const val NAME_MIN_LENGTH = 3
