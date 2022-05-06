package com.example.sliide.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sliide.R
import com.example.sliide.domain.entity.User
import com.example.sliide.domain.entity.isValid
import com.example.sliide.domain.usecase.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val UNDEFINED = -1

@HiltViewModel
class MainViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
) : ViewModel() {

    private val _usersState = MutableStateFlow<UsersFetchingState>(UsersFetchingState.Loading)
    val usersState: StateFlow<UsersFetchingState>
        get() = _usersState
    private val _showMessage = MutableStateFlow(UNDEFINED)
    val showMessage: StateFlow<Int>
        get() = _showMessage

    init {
        _showMessage.value = UNDEFINED
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            usersUseCase
                .getUsers()
                .onStart {
                    _usersState.value = UsersFetchingState.Loading
                }
                .catch {
                    _usersState.value = UsersFetchingState.Error
                }
                .collect { fetchedUsers ->
                    _usersState.value = UsersFetchingState.Success(fetchedUsers)
                }
        }
    }

    fun addUser(user: User) {
        if (!user.isValid()) {
            _showMessage.value = R.string.enter_valid_input
            return
        }
        viewModelScope.launch {
            modifyUsers {
                usersUseCase.addUser(user)
            }
        }
    }

    fun removeUser(user: User) {
        viewModelScope.launch {
            modifyUsers {
                usersUseCase.removeUser(user)
            }
        }
    }

    private suspend fun modifyUsers(block: suspend () -> Unit) {
        _usersState.value = UsersFetchingState.Loading
        try {
            block()
        } catch (throwable: Throwable) {
            _showMessage.value = R.string.something_went_wrong
        } finally {
            getUsers()
        }
    }
}
