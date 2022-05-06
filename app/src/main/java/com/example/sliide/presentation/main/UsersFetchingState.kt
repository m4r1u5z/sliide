package com.example.sliide.presentation.main

import com.example.sliide.domain.entity.User

sealed class UsersFetchingState {
    object Loading : UsersFetchingState()
    class Success(val users: List<User>) : UsersFetchingState()
    object Error : UsersFetchingState()
}
