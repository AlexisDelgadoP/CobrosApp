package com.example.cobrosapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cobrosapp.dao.UsuarioDao

class LoginViewModelFactory(private val usuarioDao: UsuarioDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(usuarioDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
