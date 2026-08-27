package com.example.cobrosapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cobrosapp.dao.UsuarioDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest


class LoginViewModel(private val usuarioDao: UsuarioDao) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun onEmailChange(newEmail: String) { _email.value = newEmail }
    fun onPasswordChange(newPass: String) { _password.value = newPass }

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = usuarioDao.getByEmail(_email.value)
            if (user != null && user.password_hash == hashPassword(_password.value)) {
                _loginState.value = LoginState.Success
                // Cambiar estado en el hilo principal
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } else {
                _loginState.value = LoginState.Error
            }
        }
    }


    private fun hashPassword(password: String): String {
        return MessageDigest.getInstance("SHA-256")
            .digest(password.toByteArray())
            .joinToString("") { "%02x".format(it) }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Success : LoginState()
    object Error : LoginState()
}
